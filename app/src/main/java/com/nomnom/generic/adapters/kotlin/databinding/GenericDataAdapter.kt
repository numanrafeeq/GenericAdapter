package com.nomnom.generic.adapters.kotlin.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class GenericDataAdapter<Model, D : ViewDataBinding>(private val list: MutableList<Model>, @LayoutRes val layout: Int) :
    RecyclerView.Adapter<GenericDataAdapter.MyViewHolder>() {


    abstract fun onBindData(model: Model, position: Int, bindingAdapter: D)
    abstract fun onItemClick(model: Model, position: Int, bindingAdapter: D)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<D>(
            LayoutInflater.from(parent.context), layout, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        onBindData(list.get(position), position, holder.binding as D)

        holder.binding.root.setOnClickListener {
            onItemClick(list[position], position, holder.binding as D )
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}