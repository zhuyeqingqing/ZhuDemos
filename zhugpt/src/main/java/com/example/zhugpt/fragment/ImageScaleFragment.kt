package com.example.zhugpt.fragment

import android.graphics.Matrix
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.zhugpt.R
import com.example.zhugpt.databinding.FragmentImageScaleBinding

class ImageScaleFragment : Fragment() {
    private var matrix = Matrix()
    private var downX = 0f
    private var downY = 0f
    private var oldDistance = 0f
    private var mode = NONE
    private var spacing = 0f
    private lateinit var mBinding: FragmentImageScaleBinding

    companion object {
        private const val NONE = 0
        private const val DRAG = 1
        private const val ZOOM = 2

        public fun newInstance(imageUrl : String) : ImageScaleFragment {
            var fragment = ImageScaleFragment()
            var bundle: Bundle = Bundle()
            bundle.putString("scale_image_url", imageUrl)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentImageScaleBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            Glide.with(it)
                .load(arguments?.getString("scale_image_url")) // 图片的 URL
                .centerCrop() // 图片显示样式，此处为居中裁剪
                .into(mBinding.scaleImage)
        } // 显示图片的 ImageView

        mBinding.scaleImage.setOnTouchListener { _, event ->
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    mode = DRAG
                    downX = event.x
                    downY = event.y
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    mode = ZOOM
                    oldDistance = getDistance(event)
                    if (oldDistance > 10f) {
                        matrix.set(mBinding.scaleImage.imageMatrix)
                    }
                    spacing = getSpacing(event)
                }
                MotionEvent.ACTION_MOVE -> {
                    if (mode == DRAG) {
                        val dx = event.x - downX
                        val dy = event.y - downY
                        matrix.postTranslate(dx, dy)
                    } else if (mode == ZOOM) {
                        val newDistance = getDistance(event)
                        if (newDistance > 10f) {
                            val scale = newDistance / oldDistance
                            matrix.set(mBinding.scaleImage.imageMatrix)
                            matrix.postScale(scale, scale, spacing, spacing)
                        }
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                    mode = NONE
                }
            }
            mBinding.scaleImage.imageMatrix = matrix
            true
        }
    }

    private fun getDistance(event: MotionEvent): Float {
        val dx = event.getX(1) - event.getX(0)
        val dy = event.getY(1) - event.getY(0)
        return Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
    }

    private fun getSpacing(event: MotionEvent): Float {
        val x = event.getX(1) + event.getX(0)
        val y = event.getY(1) + event.getY(0)
        return (x / 2 + y / 2)
    }

}
