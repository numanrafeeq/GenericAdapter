package com.nomnom.generic.adapters.kotlin.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class GenericViewAdapter<Model, Binding : ViewBinding>(private val list: MutableList<Model>) :
    RecyclerView.Adapter<GenericViewAdapter.MyViewHolder<Binding>>() {

    abstract fun onBindData(model: Model, position: Int, bindingAdapter: Binding)
    abstract fun onItemClick(model: Model, position: Int, bindingAdapter: Binding)
    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): Binding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder<Binding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = createBinding(inflater, parent)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder<Binding>, position: Int) {
        onBindData(list[position], position, holder.binding)

        holder.binding.root.setOnClickListener {
            onItemClick(list[position], position, holder.binding)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder<Binding : ViewBinding>(val binding: Binding) : RecyclerView.ViewHolder(binding.root)

}