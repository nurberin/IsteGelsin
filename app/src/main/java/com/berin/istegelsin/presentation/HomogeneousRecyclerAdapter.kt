package com.berin.istegelsin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.berin.istegelsin.databinding.AnasayfaCardBinding
import com.berin.istegelsin.domain.entity.Recipes

class HomogeneousRecyclerAdapter <Binding : ViewDataBinding, Model>(
    private val items: List<Model>,
    private val layoutId: Int,
    private val modelId: Int,
    private val onClick: (Model) -> Unit = {},
) : RecyclerView.Adapter<HomogeneousRecyclerAdapter.ViewHolder<Binding>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder<Binding>(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
    )

    override fun getItemCount() : Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder<Binding>, position: Int) {
        holder.binding.setVariable(modelId, items[position])
        (items[position] as? Recipes)?.binding = holder.binding as? AnasayfaCardBinding
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener { onClick(items[position]) }
    }

    class ViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)
}