package com.berin.istegelsin.data.remote

import com.google.gson.annotations.SerializedName

class RecipeDTO (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("imageUrl")
    var imageUrl: String? = null,
    @SerializedName("price")
    var price: Double? = null,
    @SerializedName("promotionDiscountPercentage")
    var promotionDiscountPercentage: Double? = null,

var stock : Int? =null,
    var discountedPrice: Float? = null,
)