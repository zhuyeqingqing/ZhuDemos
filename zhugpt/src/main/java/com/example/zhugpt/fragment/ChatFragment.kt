package com.example.zhugpt.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zhugpt.adapter.ChatAdapter
import com.example.zhugpt.adapter.ModelsAdapter
import com.example.zhugpt.bean.*
import com.example.zhugpt.databinding.FragmentChatBinding
import com.example.zhugpt.databinding.FragmentModelsBinding
import com.example.zhugpt.net.NetInfoPresenter

class ChatFragment : Fragment() {
    private lateinit var mBinding : FragmentChatBinding
    private var mChatList = ArrayList<ChatItem>()
    private var mMessages = ArrayList<Message>()
    private var mSessionId = ""

    companion object{
        public fun newInstance() : ChatFragment {
            return ChatFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentChatBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var linearLayoutManager  = LinearLayoutManager(context)

        mBinding.rclChat.layoutManager = linearLayoutManager
        mBinding.rclChat.adapter = ChatAdapter(mChatList)
        mBinding.btSure.setOnClickListener(this::startChat)

    }

    fun startChat(view: View){
        mChatList.add(ChatItem(1, mBinding.etAsk.text.toString()))
        mMessages.add(Message("user", mBinding.etAsk.text.toString()))
        (mBinding.rclChat.adapter as ChatAdapter).notifyDataSetChanged()
        mBinding.rclChat.smoothScrollToPosition(mChatList.size)
        hideKeyboard()
        NetInfoPresenter.getInstance().functionChat(mSessionId, mMessages, object : NetInfoPresenter.NetFeedBack() {
            override fun doSuccess(`object`: Any?) {
                if(`object` is ChatResponse){
                    mMessages.add((`object` as ChatResponse).choices[0].message)
                    var content = (`object` as ChatResponse).choices[0].message.content
                    mChatList.add(ChatItem(0, content))
                    (mBinding.rclChat.adapter as ChatAdapter).notifyDataSetChanged()
                    mBinding.rclChat.smoothScrollToPosition(mChatList.size - 1)
                }
            }

            override fun doFail() {
            }

        })

        mBinding.etAsk.text.clear()
    }


    fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mBinding.etAsk.windowToken, 0)
    }
}