package com.mstart.calendarapplication.presentation.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mstart.calendarapplication.domin.model.room.EventEntity
import com.mstart.calendarapplication.domin.repository.EventsRepository
import com.mstart.calendarapplication.domin.usecase.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val repository: EventsRepository,
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _getEventSuccess = MutableLiveData<List<EventEntity>>()
    val getEventSuccess: LiveData<List<EventEntity>> get() = _getEventSuccess

    private val _errorLiveData: MutableLiveData<Exception> = MutableLiveData(null)
    val errorLiveData = _errorLiveData

    private val _loadingData: MutableLiveData<Boolean> = MutableLiveData(false)
    val loadingData = _loadingData

    init {
        fetchEvents()
    }

    private fun fetchEvents() {
        _loadingData.value = true
        viewModelScope.launch {
            try {
                val data = getEventsUseCase.invoke()
                _getEventSuccess.value = data
            } catch (e: Exception) {
                _errorLiveData.postValue(e)
            }
            _loadingData.value = false
        }
    }

    fun delete(event: EventEntity) {
        viewModelScope.launch {
            try {
                repository.deleteEvent(event)
                fetchEvents()
            } catch (e: Exception) {
                _errorLiveData.postValue(e)
            }
        }
    }
}