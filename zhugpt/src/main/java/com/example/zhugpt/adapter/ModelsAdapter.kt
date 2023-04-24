package com.example.zhugpt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.zhugpt.bean.Model
import com.example.zhugpt.databinding.ItemModelsBinding

class ModelsAdapter(var models : List<Model>) : RecyclerView.Adapter<ModelsAdapter.ModelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelsAdapter.ModelViewHolder {
        return ModelViewHolder(ItemModelsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.binding.tvTitle.text = models[position].id
    }

    override fun getItemCount(): Int {
        return models.size
    }

    class ModelViewHolder(val binding: ItemModelsBinding) : RecyclerView.ViewHolder(binding.root)
}
