package com.berin.istegelsin.domain.entity

import com.berin.istegelsin.MainActivity
import com.berin.istegelsin.databinding.AnasayfaCardBinding

data class Recipes(
    var id: String? = null,
    var name: String? = null,
    var imageUrl: String? = null,
    var price: Double? = null,
    var promotionDiscountPercentage: Double? = null,
    var itemCount: Int = 0,
    var mainActivity: MainActivity? = null,
    var binding: AnasayfaCardBinding? = null
) : Entity()