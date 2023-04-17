package com.beauty.basicui.rcl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.beauty.basicui.R
import com.beauty.basicui.databinding.FragmentRefreshRclBinding

class RefreshRclFragment : BaseFragment() {
    private lateinit var binding : FragmentRefreshRclBinding
    private lateinit var mLayoutManager : LinearLayoutManager
    private var mList : ArrayList<String> = ArrayList()
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
        binding = FragmentRefreshRclBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.refreshlayout.setOnRefreshListener(mRefreshListener)
        mLayoutManager = LinearLayoutManager(context)
        binding.myRcl.layoutManager = mLayoutManager
        binding.myRcl.addOnScrollListener(mScrollListener)
    }

    private var mRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        Toast.makeText(context, "hah", Toast.LENGTH_SHORT)
    }

    private var mScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition()
            val itemCount = mLayoutManager.itemCount
            if(lastVisiblePosition == itemCount - 1){
                loadMore()
            }
        }
    }

    fun loadMore(){

    }

    fun loadFirstPageData(){
        for (i in 0..20){
            mList.add("第"+i+"行")
        }
    }
}