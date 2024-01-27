package com.nomnom.generic.adapters.java;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class GenericAdapter<Model, D extends ViewDataBinding> extends RecyclerView.Adapter<GenericAdapter.MyViewHolder> {

    private final List<Model> list;
    @LayoutRes
    private final int layout;

    public GenericAdapter(List<Model> list, @LayoutRes int layout) {
        this.list = list;
        this.layout = layout;
    }

    public abstract void onBindData(Model model, int position, D bindingAdapter);

    public abstract void onItemClick(Model model, int position, D bindingAdapter);

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), layout, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        onBindData(list.get(position), position, (D) holder.binding);
        holder.binding.getRoot().setOnClickListener(v ->
                onItemClick(list.get(position), position, (D) holder.binding));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final ViewDataBinding binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
