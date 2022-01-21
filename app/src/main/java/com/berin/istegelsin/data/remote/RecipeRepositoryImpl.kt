package com.berin.istegelsin.data.remote

import com.berin.istegelsin.data.RecipeRepository
import com.berin.istegelsin.domain.entity.Category
import com.berin.istegelsin.domain.entity.RecipeSearch
import com.berin.istegelsin.domain.entity.Recipes

class RecipeRepositoryImpl(private var recipesAPI: RecipesAPI):RecipeRepository {

    override suspend fun getCategory(): List<Category> {
      val response = recipesAPI.getCategorySubcategory()

        if (response.isSuccessful){
            return response.body()?.data?.map {
                Category(
                    id = it.id,
                    parentCategoryID = it.parentCategoryID,
                    name = it.name,
                    subcategories = it.subcategories
                )
            }?: listOf()
        }
        return listOf()
    }

    override suspend fun getAllRecipe(subcategoryId: String?, keyword: String?): List<Recipes> {
        var recipeSearch = RecipeSearch(subcategoryId,keyword)
        val response = recipesAPI.getSearch(recipeSearch)

        if (response.isSuccessful){
            return response.body()?.data?.map {
                Recipes(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl,
                    price = it.price,
                    promotionDiscountPercentage = it.promotionDiscountPercentage
                )
            }?: listOf()

        }
        return listOf()
    }

}