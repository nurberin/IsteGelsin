package com.berin.istegelsin.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class Util {
    companion object{
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            view.setImageResource(0)
            Glide.with(view.context).clear(view)

            if (!imageUrl.isNullOrBlank()) {
                Glide.with(view.context)
                    .load(imageUrl)
                    .into(view)
            }
        }
    }
}