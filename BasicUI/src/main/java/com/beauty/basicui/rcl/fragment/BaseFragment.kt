package com.beauty.basicui.rcl.fragment

import androidx.fragment.app.Fragment

open class BaseFragment() : Fragment() {
    constructor(name: String) : this(){
    }

    companion object{
        fun newInstance() : BaseFragment{
            return BaseFragment()
        }
    }
}