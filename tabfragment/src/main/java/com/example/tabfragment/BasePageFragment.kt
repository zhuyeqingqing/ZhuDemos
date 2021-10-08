package com.example.tabfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class BasePageFragment : Fragment() {
    public open var name : String = "默认"
}