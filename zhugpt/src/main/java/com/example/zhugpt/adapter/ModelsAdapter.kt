package com.example.zhugpt.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.zhugpt.bean.Model
import com.example.zhugpt.databinding.ItemModelsBinding

class ModelsAdapter(var models : List<Model>) : RecyclerView.Adapter<ModelsAdapter.ModelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelsAdapter.ModelViewHolder {
        return ModelViewHolder(ItemModelsBinding.inflate())
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ModelViewHolder(private val binding: ItemModelsBinding) : RecyclerView.ViewHolder(binding.root){

    }
}