package com.beauty.basicui.rcl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beauty.basicui.R

class RefreshRclFragment : BaseFragment() {
    companion object{
        fun newInstance() : BaseFragment{
            return BaseFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_refresh_rcl, container)
    }
}