package com.example.tabfragment

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup

object BaseUtil {
    fun getScreenWidth(context : Context) : Int {
        val displayMetrics : DisplayMetrics = context.resources.displayMetrics
        val width: Int = displayMetrics.widthPixels
        return width
    }

    fun setViewWidth(target: View, width: Int) {
        val targetLP = target.layoutParams
        targetLP.width = width
        target.layoutParams = targetLP
    }

    fun setViewHeight(target: View, height: Int) {
        val targetLP = target.layoutParams
        targetLP.height = height
        target.layoutParams = targetLP
    }

    fun setViewLeftMargin(target: View, leftMargin: Int) {
        val targetLP = target.layoutParams as ViewGroup.MarginLayoutParams
        targetLP.leftMargin = leftMargin
        target.layoutParams = targetLP
    }
}