package com.example.zhugpt.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.zhugpt.activity.CommonActivity.Companion.start
import com.example.zhugpt.databinding.FragmentImageBinding


class ImageFragment : Fragment() {
    private lateinit var mBinding : FragmentImageBinding

    companion object{
        public fun newInstance() : ImageFragment {
            return ImageFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentImageBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.btImageGenerate.setOnClickListener(this::gotoImageGeneratePage)
        mBinding.btImageEit.setOnClickListener(this::gotoImageEditPage)
        mBinding.btImageVariation.setOnClickListener(this::gotoImageVariationPage)
    }

    private fun gotoImageGeneratePage(view: View) {
        activity?.let { start(it, ImageGenerateFragment.newInstance()) }
    }

    private fun gotoImageEditPage(view: View) {
        activity?.let { start(it, ImageEditFragment.newInstance()) }
    }

    private fun gotoImageVariationPage(view: View) {
        activity?.let { start(it, ImageVariationFragment.newInstance()) }
    }
}