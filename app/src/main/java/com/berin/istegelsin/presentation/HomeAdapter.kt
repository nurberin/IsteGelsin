package com.berin.istegelsin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berin.istegelsin.R
import com.berin.istegelsin.databinding.AnasayfaCardBinding
import com.berin.istegelsin.databinding.CategoryItemBinding
import com.berin.istegelsin.databinding.SubcategoryItemBinding

class HomeAdapter(private var recipeList:List<Any>):RecyclerView.Adapter<HomeAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        when(viewType){
            R.layout.category_item ->{
                val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return HomeAdapterViewHolder.HomeCategoryHolder(binding)
            }
            R.layout.subcategory_item ->{
                val binding = SubcategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return HomeAdapterViewHolder.HomeSubcategoryHolder(binding)
            }
            R.layout.anasayfa_card ->{
                val binding = AnasayfaCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return HomeAdapterViewHolder.HomeCardHolder(binding)
            }
        }
        val binding = AnasayfaCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeAdapterViewHolder.HomeCardHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        when(holder){
            is HomeAdapterViewHolder.HomeCategoryHolder ->{
                holder.category.txtCategoryAdi.text = recipeList[position] as? String
            }
            is HomeAdapterViewHolder.HomeSubcategoryHolder ->{
                holder.subcategory.txtSubcategoryName.text = recipeList[position] as? String
            }
            is HomeAdapterViewHolder.HomeCardHolder ->{
                //holder.homecard.recipe = recipeList //burada sıkıntı olabilir
            }
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun getItemViewType(position: Int): Int {
        when(recipeList[position]){
            is String->{
                // burada idler karşılaştırılacak. eğer subcategorisi null ise subcategori basılacak
            }
        }
        return R.layout.anasayfa_card
    }
}