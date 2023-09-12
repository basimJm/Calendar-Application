package com.mstart.calendarapplication.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mstart.calendarapplication.R
import com.mstart.calendarapplication.core.base.BaseFragment
import com.mstart.calendarapplication.databinding.FragmentWebViewBinding

@SuppressLint("ResourceType")
class WebFragment:BaseFragment<FragmentWebViewBinding>(R.id.webFragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentWebViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initWebView()
    }

    private fun initWebView() {
        binding.wvTest.loadUrl("https://tamatemplus.com/home")
    }
}