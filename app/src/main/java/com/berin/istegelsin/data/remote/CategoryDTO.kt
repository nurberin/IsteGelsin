package com.berin.istegelsin.data.remote

import com.berin.istegelsin.domain.entity.Category
import com.google.gson.annotations.SerializedName

class CategoryDTO (
    @SerializedName("id")var id: String? = null,
    @SerializedName("parentCategoryID")var parentCategoryID: String? = null,
    @SerializedName("name")var name: String? = null,
    @SerializedName("subcategories")var subcategories: List<Category>? = null
        )