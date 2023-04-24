package com.example.zhugpt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.zhugpt.bean.ChatItem
import com.example.zhugpt.bean.Model
import com.example.zhugpt.databinding.ItemChatLeftBinding
import com.example.zhugpt.databinding.ItemChatRightBinding
import com.example.zhugpt.databinding.ItemModelsBinding

class ChatAdapter(var chats : List<ChatItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            else ->  ChatLeftViewHolder(
                ItemChatLeftBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ChatLeftViewHolder -> holder.binding.tvChatContent.text = chats[position].content
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

    class ChatLeftViewHolder(val binding: ItemChatLeftBinding) : RecyclerView.ViewHolder(binding.root)

    class ChatRightViewHolder(val binding: ItemChatRightBinding) : RecyclerView.ViewHolder(binding.root)
}
