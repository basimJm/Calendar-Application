package com.mstart.calendarapplication.presentation.ui.event

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mstart.calendarapplication.R
import com.mstart.calendarapplication.core.base.BaseFragment
import com.mstart.calendarapplication.databinding.FragmentEventBinding
import com.mstart.calendarapplication.domin.model.room.EventEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("ResourceType")
@AndroidEntryPoint
class EventFragment : BaseFragment<FragmentEventBinding>(R.id.eventFragment),
    OnSelectionChangedListener {

    private lateinit var adapter: EventAdapter
    private val viewModel: EventViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserver()
        initListener()

    }

    private fun initListener() {
        binding.toolbarEvent.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.item_delete -> {
                    val selectedItem = adapter.eventItems.filter { it.isSelected }.toList()
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle(getString(R.string.delete_event))
                    builder.setMessage(getString(R.string.are_you_sure))
                    builder.setPositiveButton(getString(R.string.delete)) { dialog, _ ->
                        if (selectedItem.isNotEmpty()) {
                            for (event in selectedItem) {
                                viewModel.delete(event)
                            }
                            dialog.dismiss()
                        } else {
                            dialog.dismiss()
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.select_item_to_delete), Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    builder.show()
                    true
                }

                R.id.item_back -> {
                    findNavController().navigate(R.id.calendarFragment)
                    true
                }

                else -> false

            }
        }

    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.getEventSuccess.observe(viewLifecycleOwner, Observer { data ->
                if (data != null) {
                    adapter =
                        EventAdapter(data, ::onEventDelete, ::onUpdateEvent, this@EventFragment)
                    binding.rvEvent.adapter = adapter
                    if (adapter.itemCount == 0) {
                        binding.rvEvent.visibility = View.GONE
                        binding.cvEmpty.visibility = View.VISIBLE
                    } else {
                        binding.rvEvent.visibility = View.VISIBLE
                        binding.cvEmpty.visibility = View.GONE
                    }
                }
            })
        }
        lifecycleScope.launch {
            viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
                Log.d(getString(R.string.error), getString(R.string.handle_error, error))
            })
        }
        lifecycleScope.launch {
            viewModel.loadingData.observe(viewLifecycleOwner, Observer { loading ->
                binding.pbEvent.isVisible = loading
            })
        }
    }

    private fun onEventDelete(event: EventEntity) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.delete_event))
        builder.setMessage(getString(R.string.are_you_sure))
        builder.setPositiveButton(getString(R.string.delete)) { dialog, _ ->
            viewModel.delete(event)
            dialog.dismiss()
        }
        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun onUpdateEvent(event: EventEntity) {
        val bundle = bundleOf().apply {
            putParcelable("event" ,  event)
        }
        findNavController().navigate(R.id.editFragment, bundle)
    }

    override fun onSelectionChanged(isSelected: Boolean) {
        binding.toolbarEvent.menu.findItem(R.id.item_delete).isVisible = isSelected

    }
}