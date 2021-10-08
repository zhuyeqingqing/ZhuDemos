package com.example.tabfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val fragment1 : Fragment1 = Fragment1()
        val fragment2 : Fragment2 = Fragment2()
        val fragment3 : Fragment3 = Fragment3()
        val fragment4 : Fragment4 = Fragment4()
        val fragments : ArrayList<BasePageFragment> = ArrayList()
        fragments.add(fragment1)
        fragments.add(fragment2)
        fragments.add(fragment3)
        fragments.add(fragment4)
        Log.i("hehe","fragments.add(fragment4)")


        indicator_fragment_layout.mFragments = fragments
        indicator_fragment_layout.initView(supportFragmentManager,layoutInflater)

    }


}