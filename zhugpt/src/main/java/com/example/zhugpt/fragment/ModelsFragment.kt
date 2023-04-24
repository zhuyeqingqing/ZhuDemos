package com.example.zhugpt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zhugpt.adapter.ModelsAdapter
import com.example.zhugpt.bean.Model
import com.example.zhugpt.bean.ModelApiObject
import com.example.zhugpt.databinding.FragmentModelsBinding
import com.example.zhugpt.net.NetInfoPresenter

class ModelsFragment : Fragment() {
    private lateinit var mBinding : FragmentModelsBinding

    companion object{
        public fun newInstance() : ModelsFragment {
            return ModelsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentModelsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var linearLayoutManager  = LinearLayoutManager(context)

        mBinding.rclModels.layoutManager = linearLayoutManager

        NetInfoPresenter.getInstance().getApiModels(object : NetInfoPresenter.NetFeedBack() {
            override fun doSuccess(`object`: Any?) {
                if(`object` is ModelApiObject){
                    var models = (`object` as ModelApiObject).data
                    var adapter = models?.let { ModelsAdapter(it) }
                    mBinding.rclModels.adapter = adapter
                }
            }

            override fun doFail() {
            }

        })
    }
}