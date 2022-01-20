package com.berin.istegelsin.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.berin.istegelsin.databinding.AnasayfaCardBinding
import com.berin.istegelsin.databinding.CategoryItemBinding
import com.berin.istegelsin.databinding.SubcategoryItemBinding

sealed class HomeAdapterViewHolder(val binding: View):RecyclerView.ViewHolder(binding) {
    class HomeCategoryHolder(val category:CategoryItemBinding):HomeAdapterViewHolder(category.root)
    class HomeSubcategoryHolder(val subcategory:SubcategoryItemBinding):HomeAdapterViewHolder(subcategory.root)
    class HomeCardHolder(val homecard:AnasayfaCardBinding):HomeAdapterViewHolder(homecard.root)
}