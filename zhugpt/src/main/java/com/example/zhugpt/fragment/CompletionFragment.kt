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
import com.example.zhugpt.databinding.FragmentCompletionBinding
import com.example.zhugpt.databinding.FragmentModelsBinding
import com.example.zhugpt.net.NetInfoPresenter

class CompletionFragment : Fragment() {
    private lateinit var mBinding : FragmentCompletionBinding
    private var mCompletionList = ArrayList<ChatItem>()

    companion object{
        public fun newInstance() : CompletionFragment {
            return CompletionFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCompletionBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var linearLayoutManager  = LinearLayoutManager(context)

        mBinding.rclChat.layoutManager = linearLayoutManager
        mBinding.rclChat.adapter = ChatAdapter(mCompletionList)
        mBinding.btSure.setOnClickListener(this::startChat)

    }

    fun startChat(view: View){
        mCompletionList.add(ChatItem(1, mBinding.etAsk.text.toString()))
        (mBinding.rclChat.adapter as ChatAdapter).notifyDataSetChanged()
        mBinding.rclChat.smoothScrollToPosition(mCompletionList.size)
        hideKeyboard()
        NetInfoPresenter.getInstance().functionCompletion(object : NetInfoPresenter.NetFeedBack() {
            override fun doSuccess(`object`: Any?) {
                if(`object` is CompletionResponse){
                    var content = (`object` as CompletionResponse).choices[0].text
                    mCompletionList.add(ChatItem(0, content))
                    (mBinding.rclChat.adapter as ChatAdapter).notifyDataSetChanged()
                    mBinding.rclChat.smoothScrollToPosition(mCompletionList.size - 1)
                }
            }

            override fun doFail() {
            }

        }, mBinding.etAsk.text.toString())

        mBinding.etAsk.text.clear()
    }


    fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mBinding.etAsk.windowToken, 0)
    }
}