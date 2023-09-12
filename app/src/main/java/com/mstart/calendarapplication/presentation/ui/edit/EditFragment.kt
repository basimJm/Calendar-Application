package com.mstart.calendarapplication.presentation.ui.edit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mstart.calendarapplication.R
import com.mstart.calendarapplication.core.base.BaseFragment
import com.mstart.calendarapplication.databinding.FragmentEditBinding
import com.mstart.calendarapplication.domin.model.room.EventEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("ResourceType")
@AndroidEntryPoint
class EditFragment : BaseFragment<FragmentEditBinding>(R.id.editFragment) {

    private val viewModel: EditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserver()
        initListener()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.getEventSuccess.observe(viewLifecycleOwner, Observer { event ->
                binding.event = event
            })
        }
        lifecycleScope.launch {
            viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
                Log.d(getString(R.string.error), getString(R.string.handle_error, error))
            })
        }
        lifecycleScope.launch {
            viewModel.loadingData.observe(viewLifecycleOwner, Observer { loading ->
                binding.pbEdit.isVisible = loading
            })
        }
    }

    private fun initListener() {
        onBackListener()
        onSaveListener()
    }

    private fun onBackListener() {
        binding.tbEdit.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.item_back -> {
                    findNavController().navigate(R.id.eventFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun onSaveListener() {
        val event = requireArguments().getParcelable<EventEntity>("event")
        binding.event = event

        binding.btnSaveEdit.setOnClickListener {
            if (event != null) {
                if (binding.edDescUpdate.length() >= 1) {
                    event.eventDesc = binding.edDescUpdate.text.toString()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.success_edit), Toast.LENGTH_SHORT
                    ).show()
                    viewModel.updateEvent(event)
                    findNavController().navigate(R.id.eventFragment)

                } else {
                    Toast.makeText(requireContext(), getString(R.string.empty), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}