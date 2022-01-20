package com.berin.istegelsin.data

import com.berin.istegelsin.data.remote.CategoryDTO
import com.berin.istegelsin.data.remote.RecipeDTO
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("data")
    var data: List<RecipeDTO>? = null
)

data class CategoryResponse(
    @SerializedName("data")
    var data: List<CategoryDTO>? = null
)

