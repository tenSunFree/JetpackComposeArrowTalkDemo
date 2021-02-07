package com.example.jetpackcomposearrowtalkdemo.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposearrowtalkdemo.R
import com.example.jetpackcomposearrowtalkdemo.common.JCATDApplication
import com.example.jetpackcomposearrowtalkdemo.common.di.AppComponent
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    val appComponent: AppComponent by lazy {
        (activity?.application as JCATDApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_base, container, false)
}

interface Actions