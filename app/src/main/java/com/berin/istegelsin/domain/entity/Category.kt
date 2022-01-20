package com.berin.istegelsin.domain.entity


data class Category(
    var id: String? = null,
    var parentCategoryID: String? = null,
    var name: String? = null,
    var subcategories: List<Category>? = null
) : Entity()