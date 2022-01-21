package com.berin.istegelsin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.berin.istegelsin.databinding.ActivityMainBinding
import com.berin.istegelsin.databinding.AnasayfaCardBinding
import com.berin.istegelsin.databinding.CategoryItemBinding
import com.berin.istegelsin.databinding.SubcategoryItemBinding
import com.berin.istegelsin.domain.entity.Category
import com.berin.istegelsin.domain.entity.Recipes
import com.berin.istegelsin.presentation.HomeUIState
import com.berin.istegelsin.presentation.HomeViewModel
import com.berin.istegelsin.presentation.HomogeneousRecyclerAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    homeViewModel.getRecipes("", it)
                    lifecycleScope.launch {
                        homeViewModel.homeState.collect {
                            when (it) {
                                is HomeUIState.Success -> {
                                    it.recipeList.let { recipes ->
                                        recipes.forEach {
                                            it.mainActivity = this@MainActivity
                                        }
                                        binding.recipeRv.adapter =
                                            HomogeneousRecyclerAdapter<AnasayfaCardBinding, Recipes>(
                                                recipes,
                                                R.layout.anasayfa_card,
                                                BR.recipe
                                            ) {
                                            }
                                    }
                                }
                            }
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        lifecycleScope.launch {
            homeViewModel.homeState.collect {
                when (it) {
                    is HomeUIState.PageSuccessCategory -> {

                        it.categoryList.let {
                            homeViewModel.getRecipes(it?.get(0)?.subcategories?.get(0)?.id!!, "")
                            binding.subcategoryRv.adapter =
                                HomogeneousRecyclerAdapter<SubcategoryItemBinding, Category>(
                                    it.get(0).subcategories!!,
                                    R.layout.subcategory_item,
                                    BR.subcategory
                                ) {
                                    homeViewModel.getRecipes(it.id!!, "")
                                }
                            binding.categoryRv.adapter =
                                HomogeneousRecyclerAdapter<CategoryItemBinding, Category>(
                                    it,
                                    R.layout.category_item,
                                    BR.category
                                )
                                {
                                    binding.subcategoryRv.adapter =
                                        HomogeneousRecyclerAdapter<SubcategoryItemBinding, Category>(
                                            it.subcategories!!,
                                            R.layout.subcategory_item,
                                            BR.subcategory
                                        ) {
                                            homeViewModel.getRecipes(it.id!!, "")
                                        }
                                }
                            // binding.subcategoryRv.adapter = HomogeneousRecyclerAdapter<SubcategoryItemBinding,Category>(it,R.layout.subcategory_item,BR.subcategory)
                        }
                    }
                    is HomeUIState.Success -> {
                        it.recipeList.let { recipes ->

                            recipes.forEach {
                                it.mainActivity = this@MainActivity
                            }
                            binding.recipeRv.adapter =
                                HomogeneousRecyclerAdapter<AnasayfaCardBinding, Recipes>(
                                    recipes,
                                    R.layout.anasayfa_card,
                                    BR.recipe
                                ) {
                                }
                        }
                    }
                }
            }
        }
        homeViewModel.getCategories()
    }

    fun clickMethodMy(view: View, recipe: Recipes) {

        recipe.itemCount = recipe.itemCount+ 1
        binding.recipeRv.adapter?.notifyDataSetChanged()
    }

    fun clickDelete(view: View, recipe: Recipes) {

        recipe.itemCount = recipe.itemCount - 1

        if(recipe.itemCount < 0) recipe.itemCount = 0

        binding.recipeRv.adapter?.notifyDataSetChanged()
    }
}