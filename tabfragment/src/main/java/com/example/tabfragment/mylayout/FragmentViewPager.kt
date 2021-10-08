package com.example.tabfragment.mylayout

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import java.lang.Exception


class FragmentViewPager : ViewPager {
    constructor(context : Context) : super(context) {

    }

    constructor(context: Context, abstractSet: AttributeSet) : super(context, abstractSet){

    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        return try {
            super.onInterceptTouchEvent(event)
        } catch (e: Exception) {
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return try {
            super.onTouchEvent(ev)
        } catch (e: Exception) {
            false
        }
    }

    override fun setCurrentItem(item: Int) {
        try {
            super.setCurrentItem(item)
        } catch (e: Exception) {
        }
    }

}