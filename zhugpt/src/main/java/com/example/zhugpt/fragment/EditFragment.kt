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
import com.example.zhugpt.databinding.FragmentEditBinding
import com.example.zhugpt.databinding.FragmentModelsBinding
import com.example.zhugpt.net.NetInfoPresenter

class EditFragment : Fragment() {
    private lateinit var mBinding : FragmentEditBinding
    private var mChatList = ArrayList<ChatItem>()

    companion object{
        public fun newInstance() : EditFragment {
            return EditFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentEditBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var linearLayoutManager  = LinearLayoutManager(context)

        mBinding.rclEdit.layoutManager = linearLayoutManager
        mBinding.rclEdit.adapter = ChatAdapter(mChatList)
        mBinding.btSure.setOnClickListener(this::startChat)

    }

    fun startChat(view: View){
        mChatList.add(ChatItem(1, mBinding.etAsk.text.toString()))
        (mBinding.rclEdit.adapter as ChatAdapter).notifyDataSetChanged()
        mBinding.rclEdit.smoothScrollToPosition(mChatList.size)
        hideKeyboard()
        NetInfoPresenter.getInstance().functionEdit(mBinding.etAsk.text.toString(), mBinding.etInstruction.text.toString(), object : NetInfoPresenter.NetFeedBack() {
            override fun doSuccess(`object`: Any?) {
                if(`object` is EditResponse){
                    var content = (`object` as EditResponse).choices[0].text
                    mChatList.add(ChatItem(0, content))
                    (mBinding.rclEdit.adapter as ChatAdapter).notifyDataSetChanged()
                    mBinding.rclEdit.smoothScrollToPosition(mChatList.size - 1)
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