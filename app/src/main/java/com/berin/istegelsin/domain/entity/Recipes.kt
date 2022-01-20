package com.berin.istegelsin.domain.entity

import com.berin.istegelsin.MainActivity

data class Recipes(
    var id: String? = null,
    var name: String? = null,
    var imageUrl: String? = null,
    var price: Double? = null,
    var promotionDiscountPercentage: Double? = null,
    var itemCount: String? = null,
    var mainActivity: MainActivity? = null
) : Entity()