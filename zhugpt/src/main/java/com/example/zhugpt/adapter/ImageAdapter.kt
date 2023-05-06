package com.example.zhugpt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zhugpt.R
import com.example.zhugpt.bean.ChatItem
import com.example.zhugpt.databinding.ItemChatRightBinding
import com.example.zhugpt.databinding.ItemImageLeftBinding
import com.example.zhugpt.databinding.ItemModelsBinding

class ImageAdapter(var context : Context, var chats : List<ChatItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TYPE_1 = 1
        const val TYPE_2 = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_2 -> ChatRightViewHolder(
                ItemChatRightBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else ->  ImageLeftViewHolder(
                ItemImageLeftBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ImageLeftViewHolder -> {
                Glide.with(context)
                    .load(chats[position].content) // 图片的 URL
                    .centerCrop() // 图片显示样式，此处为居中裁剪
                    .into(holder.binding.ivImage) // 显示图片的 ImageView
            }
            is ChatRightViewHolder -> holder.binding.tvChatContentRight.text = chats[position].content
        }
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(chats[position].who){
            1 -> TYPE_2
            else -> TYPE_1
        }
    }

    class ImageLeftViewHolder(val binding: ItemImageLeftBinding) : RecyclerView.ViewHolder(binding.root)

    class ChatRightViewHolder(val binding: ItemChatRightBinding) : RecyclerView.ViewHolder(binding.root)
}
