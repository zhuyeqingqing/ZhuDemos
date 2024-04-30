package com.example.zhugpt.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zhugpt.adapter.ChatAdapter
import com.example.zhugpt.adapter.ImageAdapter
import com.example.zhugpt.bean.*
import com.example.zhugpt.databinding.*
import com.example.zhugpt.net.NetInfoPresenter
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ImageEditFragment : Fragment() {
    private lateinit var mBinding : FragmentImageEditBinding
    private var mChatList = ArrayList<ChatItem>()

    companion object{
        public fun newInstance() : ImageEditFragment {
            return ImageEditFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentImageEditBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var linearLayoutManager  = LinearLayoutManager(context)

        mBinding.rclEdit.layoutManager = linearLayoutManager
        mBinding.rclEdit.adapter = activity?.let { ImageAdapter(it, mChatList) }
        mBinding.btSure.setOnClickListener(this::startGetImage)

        Thread(Runnable { // 获取目标文件的路径
            val targetFilePath = context?.filesDir?.path + File.separator + "image.png"

// 创建目标文件
            val targetFile = File(targetFilePath)

// 检查目标文件是否已存在
            if (!targetFile.exists()) {
                // 打开 assets 中的图片文件
                val inputStream: InputStream = context?.assets!!.open("168328482329003_P21810815.webp")

                // 将输入流写入目标文件
                FileOutputStream(targetFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }

                // 关闭输入流
                inputStream.close()
            }
     }).start()

    }

    private fun startGetImage(view: View){
        mChatList.add(ChatItem(1, mBinding.etPrompt.text.toString()))
        (mBinding.rclEdit.adapter as ImageAdapter).notifyDataSetChanged()
        mBinding.rclEdit.smoothScrollToPosition(mChatList.size)
        hideKeyboard()
        context?.let {
            NetInfoPresenter.getInstance().functionImageEdit(
                it, mBinding.etImageNum.text.toString().toIntOrNull(),
                mBinding.etSize.text.toString(), mBinding.etPrompt.text.toString(), object : NetInfoPresenter.NetFeedBack() {
                    override fun doSuccess(`object`: Any?) {
                        if(`object` is ImageResponse){
                            var images = (`object` as ImageResponse).data
                            if (images != null && images.size > 0){
                                for (image in images){
                                    mChatList.add(ChatItem(0, image.url))
                                }
                                (mBinding.rclEdit.adapter as ImageAdapter).notifyDataSetChanged()
                                mBinding.rclEdit.smoothScrollToPosition(mChatList.size - 1)
                            }
                        }
                    }

                    override fun doFail() {
                    }

                })
        }

        mBinding.etPrompt.text.clear()
    }


    fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mBinding.etPrompt.windowToken, 0)
    }
}