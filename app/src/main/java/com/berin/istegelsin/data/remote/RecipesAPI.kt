package com.berin.istegelsin.data.remote

import com.berin.istegelsin.data.CategoryResponse
import com.berin.istegelsin.data.RecipeResponse
import com.berin.istegelsin.domain.entity.RecipeSearch
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RecipesAPI {

    @GET("market/category/template")
    suspend fun getCategorySubcategory():Response<CategoryResponse>

   @POST("market/product/template")
   suspend fun getSearch(@Body recipeSearch: RecipeSearch): Response<RecipeResponse>

}