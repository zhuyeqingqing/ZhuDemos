package com.example.zhugpt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.zhugpt.bean.Model
import com.example.zhugpt.databinding.FragmentModelsBinding

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
        var model : Model = Model()
    }
}