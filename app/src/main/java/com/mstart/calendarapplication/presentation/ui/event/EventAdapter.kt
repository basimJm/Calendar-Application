package com.mstart.calendarapplication.presentation.ui.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mstart.calendarapplication.databinding.EventItemListBinding
import com.mstart.calendarapplication.domin.model.room.EventEntity

class EventAdapter(
    private val eventItem: List<EventEntity>,
    private val onEventDelete: (EventEntity) -> Unit,
    private val onUpdateEvent: (EventEntity) -> Unit,
    private val onSelectionChangedListener: OnSelectionChangedListener
) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var eventItems = eventItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding =
            EventItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return eventItem.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventItem[position]
        holder.bind(event)
    }

    inner class EventViewHolder(private val binding: EventItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventEntity) {

            binding.event = event

            binding.ivDelete.setOnClickListener {
                onEventDelete.invoke(event)
            }

            binding.cbSelect.isSelected = event.isSelected
            binding.cbSelect.setOnCheckedChangeListener { _, isChecked ->
                event.isSelected = isChecked
                onSelectionChangedListener.onSelectionChanged(eventItems.any { it.isSelected })
            }

            binding.ivEdit.setOnClickListener {
                onUpdateEvent.invoke(event)
            }
        }
    }
}