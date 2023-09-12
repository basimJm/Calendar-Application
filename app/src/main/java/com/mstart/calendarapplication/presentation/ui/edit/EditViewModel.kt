package com.mstart.calendarapplication.presentation.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mstart.calendarapplication.domin.model.room.EventEntity
import com.mstart.calendarapplication.domin.repository.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(private val repository: EventsRepository) : ViewModel() {

    private val _getEventSuccess = MutableLiveData<EventEntity>()
    val getEventSuccess: LiveData<EventEntity> get() = _getEventSuccess

    private val _errorLiveData: MutableLiveData<Exception> = MutableLiveData(null)
    val errorLiveData = _errorLiveData

    private val _loadingData: MutableLiveData<Boolean> = MutableLiveData(false)
    val loadingData = _loadingData

    fun updateEvent(event: EventEntity) {
        viewModelScope.launch {
            repository.updateEvent(event)
        }
    }
}