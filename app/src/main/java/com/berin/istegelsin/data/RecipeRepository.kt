package com.berin.istegelsin.data

import com.berin.istegelsin.domain.entity.Category
import com.berin.istegelsin.domain.entity.Recipes

interface RecipeRepository {
    suspend fun getCategory():List<Category>
    suspend fun getAllRecipe(subcategoryId: String?,keyword:String?):List<Recipes>

//    suspend fun getSearch(keyword:String?):List<Recipes>


}