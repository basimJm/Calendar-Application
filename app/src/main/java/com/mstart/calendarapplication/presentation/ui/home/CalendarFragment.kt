package com.mstart.calendarapplication.presentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mstart.calendarapplication.R
import com.mstart.calendarapplication.core.base.BaseFragment
import com.mstart.calendarapplication.databinding.FragmentCalnedarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@SuppressLint("ResourceType")
@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalnedarBinding>(R.id.calendarFragment) {

    private val viewModel: CalendarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalnedarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserver()
        initListener()
    }

    private fun initListener() {
        convertListener()
        onAddEvent()
        onWebView()
    }

    private fun onWebView() {
        binding.btnWeb.setOnClickListener {
            findNavController().navigate(R.id.webFragment)
        }
    }

    private fun onAddEvent() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.eventBottomDialog)
        }
    }

    private fun convertListener() {
        fun isValidGregorianDate(date: String): Boolean {
            val pattern = getString(R.string.dd_mm_yyyy).toRegex()
            return pattern.matches(date)
        }
        binding.btnConvert.setOnClickListener {
            val date = binding.etDate.text.toString()
            if (isValidGregorianDate(date)) {
                viewModel.getHijriData(date)
            } else {
                Toast.makeText(context, getString(R.string.invalid_date_format), Toast.LENGTH_SHORT)
                    .show()
                binding.etDate.error = getString(R.string.date_incorrect)
            }
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.getHijriDateSuccess.observe(viewLifecycleOwner, Observer { response ->
                if (response != null) {
                    binding.calendarData = response
                    binding.cvHomeEmpty.visibility = View.GONE
                } else {
                    binding.cvHomeEmpty.visibility = View.VISIBLE
                }
            })
        }
        lifecycleScope.launch {
            viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { e ->
                Log.d(getString(R.string.tagoo), getString(R.string.erorr, e))
            })
        }
        lifecycleScope.launch {
            viewModel.loadingData.observe(viewLifecycleOwner, Observer { isLoading ->
                binding.pbConvert.isVisible = isLoading
            })
        }
    }
}


