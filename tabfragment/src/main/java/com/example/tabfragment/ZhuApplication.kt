package com.example.tabfragment

import android.app.Application

class ZhuApplication : Application() {
    companion object {
        private var mInstance : ZhuApplication ? = null
        fun getInstance() : ZhuApplication? {
            return mInstance;
        }
    }


    override fun onCreate() {
        super.onCreate()
        mInstance = this;
    }

    fun getCustomTheme() : Int {
        return ThemeType.DEFAULT;
    }


}