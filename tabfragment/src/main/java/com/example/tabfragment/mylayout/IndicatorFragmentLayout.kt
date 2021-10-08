package com.example.tabfragment.mylayout

import android.app.Activity
import android.content.AttributionSource
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.tabfragment.BasePageFragment
import com.example.tabfragment.R
import com.example.tabfragment.R.layout.*
import kotlinx.android.synthetic.main.indicator_page_layout.view.*
import java.lang.Exception
import java.lang.NullPointerException

class IndicatorFragmentLayout : LinearLayout{
    var  mFragments : ArrayList<BasePageFragment> ? = ArrayList<BasePageFragment>()
    var mAdapter : BaseFragmentPagerAdapter ? = null;
    var mFragmentManager : FragmentManager ? = null;
    private var mCurrFragmentPos = 0
    constructor(context : Context) : super(context){
        Log.i("hehe","constructor(context : Context)")
        LayoutInflater.from(context).inflate(R.layout.indicator_page_layout,null)
    }


    constructor(context: Context, attrs : AttributeSet) : super(context, attrs){
        Log.i("hehe","constructor(context: Context, attrs : AttributeSet) ")
        LayoutInflater.from(context).inflate(R.layout.indicator_page_layout,null)
    }

    override fun onFinishInflate() {
        Log.i("hehe","onFinishInflate()")
        super.onFinishInflate()

    }

    fun initView(supportFragmentManager: FragmentManager,inflater: LayoutInflater){
        view_pager.setOffscreenPageLimit(4);

        for (index in mFragments?.indices!!){
            viewpager_tabbar.addTab(mFragments!!.get(index).name)
        }

        mAdapter = BaseFragmentPagerAdapter(supportFragmentManager, mFragments);
        view_pager.setAdapter(mAdapter);
        viewpager_tabbar.setOnTabClickListener(mOnTabClickListener);
        view_pager.setOnPageChangeListener(mOnViewPagerChangeListener);
        addView(rootView)
    }

    private val mOnTabClickListener: ViewPagerTabBar.OnTabClickListener = object :
        ViewPagerTabBar.OnTabClickListener {
        override fun onItemClick(position: Int) {
            try {
                view_pager.setCurrentItem(position)
                mAdapter?.notifyDataSetChanged()
            } catch (e: NullPointerException) { // avoid NullPointerException when setUserVisibleHint
            }
        }
    }

    private val mOnViewPagerChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        private var mLastFragmentPos: Int = mCurrFragmentPos
        override fun onPageScrollStateChanged(state: Int) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (mLastFragmentPos != mCurrFragmentPos) mFragments?.get(mLastFragmentPos)?.onPause()
                mFragments?.get(mCurrFragmentPos)?.onResume()
                mLastFragmentPos = mCurrFragmentPos
            }
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            viewpager_tabbar.updateActionBar(position, positionOffset)
        }

        override fun onPageSelected(position: Int) {
            mCurrFragmentPos = position
        }
    }

    private fun removeAllFragments() {
        if (mFragments == null || mFragments!!.size == 0) return
        try {
            view_pager.setOnPageChangeListener(null)
            val fragmentTransaction = mFragmentManager?.beginTransaction()
            for (fragment in mFragments!!) {
                try {
                    if (fragment.view != null) {
                        (fragment.view as ViewGroup).removeAllViews()
                    }
                } catch (e: Exception) {
                }
                if (fragmentTransaction != null) {
                    fragmentTransaction.remove(fragment)
                }
            }
            if (fragmentTransaction != null) {
                fragmentTransaction.commitAllowingStateLoss()
            }
        } catch (e: Exception) {
        } finally {
            if (mFragments != null) mFragments!!.clear()
        }
    }


}