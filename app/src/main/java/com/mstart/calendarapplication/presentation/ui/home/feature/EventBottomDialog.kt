package com.mstart.calendarapplication.presentation.ui.home.feature

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mstart.calendarapplication.R
import com.mstart.calendarapplication.databinding.BottomShetDialogBinding
import com.mstart.calendarapplication.domin.model.room.EventEntity
import com.mstart.calendarapplication.presentation.ui.home.CalendarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
class EventBottomDialog : BottomSheetDialogFragment() {

    private lateinit var binding: BottomShetDialogBinding
    private val viewModel: CalendarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomShetDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getHijriData(binding.tvGreDate.text.toString())
        initObserver()
        insertEvent()
    }

    @SuppressLint("StringFormatInvalid")
    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.getHijriDateSuccess.observe(viewLifecycleOwner, Observer { response ->
                if (response != null) {
                    binding.calendarData = response
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
                if (isLoading) {
                    binding.pbDialog.visibility = View.VISIBLE
                } else {
                    binding.pbDialog.visibility = View.GONE
                }
            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertEvent() {
        binding.tvDateTime.text = LocalDateTime.now().toLocalTime().toString()

        binding.btnSave.setOnClickListener {
            val eventTitle = binding.etEventTitle.text.toString()
            val eventDesc = binding.etEventDesc.text.toString()

            if (eventTitle.isNotEmpty() && eventDesc.isNotEmpty()) {
                viewModel.insertEvent(
                    EventEntity(
                        dateGregorian = binding.tvGreDate.text.toString(),
                        dateHijri = binding.tvDate.text.toString(),
                        day = binding.tvDay.text.toString(),
                        month = binding.tvMonth.text.toString(),
                        eventTitle = eventTitle,
                        eventDesc = eventDesc,
                        serverDateTime = binding.tvDateTime.text.toString()
                    )
                )
                findNavController().navigate(R.id.eventFragment)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.event_added),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (eventTitle.isEmpty()) binding.etEventTitle.error =
                    getString(R.string.field_empty)
                if (eventDesc.isEmpty()) binding.etEventDesc.error = getString(R.string.field_empty)
                Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}