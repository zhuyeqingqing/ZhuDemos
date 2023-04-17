package com.beauty.basicui.rcl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beauty.basicui.databinding.ItemRclLoadMoreBinding
import com.beauty.basicui.databinding.ItemRclStrBinding
import java.util.zip.Inflater

class RefreshAdapter(private var mData : List<String>) : RecyclerView.Adapter<RefreshAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemRclStrBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    class LoadMoreHolder(private val binding: ItemRclLoadMoreBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRclStrBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
