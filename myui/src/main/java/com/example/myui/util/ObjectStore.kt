package com.example.myui.util

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import java.util.*

class ObjectStore {
    private fun ObjectStore() {}

    companion object {
        private var mObjects: HashMap<String?, Any?> = HashMap<String?, Any?>()
        private var mContext: Context? = null
        private var mLaunchTime: Long = 0

        fun add(obj: Any?): String? {
            val key = UUID.randomUUID().toString()
            synchronized(mObjects) {
                mObjects[key] = obj
                return key
            }
        }

        fun add(key: String?, obj: Any?) {
            synchronized(mObjects) { mObjects.put(key, obj) }
        }

        operator fun get(key: String?): Any? {
            var obj: Any? = null
            synchronized(mObjects) {
                obj = mObjects[key]
                return obj
            }
        }

        fun remove(key: String?): Any? {
            var obj: Any? = null
            synchronized(mObjects) {
                obj = mObjects.remove(key)
                return obj
            }
        }

        fun setContext(context: Context?) {
            if (context != null) {
                mContext = context.applicationContext
            }
        }

        fun setContextOfLanguage(context: Context?) {
            if (context != null && context !is Activity) {
                mContext = context
            }
        }

        fun getContext(): Context? {
            return mContext
        }

        fun setLaunchTime(launchTime: Long) {
            mLaunchTime = launchTime
        }

        fun getLaunchTime(): Long {
            return mLaunchTime
        }

        fun getLaunchDuration(): Long {
            return if (mLaunchTime == 0L) -1L else SystemClock.elapsedRealtime() - mLaunchTime
        }
    }

}