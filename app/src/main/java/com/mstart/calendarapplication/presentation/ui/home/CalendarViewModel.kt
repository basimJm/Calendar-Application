package com.mstart.calendarapplication.presentation.ui.home


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mstart.calendarapplication.domin.model.response.DateResponseRemoteModel
import com.mstart.calendarapplication.domin.model.room.EventEntity
import com.mstart.calendarapplication.domin.repository.EventsRepository
import com.mstart.calendarapplication.domin.usecase.CalendarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarUseCase: CalendarUseCase,
    private val repository: EventsRepository,
) : ViewModel() {
    private val _getHijriDateSuccess: MutableLiveData<DateResponseRemoteModel?> = MutableLiveData()
    val getHijriDateSuccess: LiveData<DateResponseRemoteModel?> = _getHijriDateSuccess

    private val _errorLiveData: MutableLiveData<Exception> = MutableLiveData(null)
    val errorLiveData = _errorLiveData

    private val _loadingData: MutableLiveData<Boolean> = MutableLiveData(false)
    val loadingData = _loadingData


    fun getHijriData(gregorianDate: String) {
        viewModelScope.launch {
            _loadingData.value = true
            try {
                val respones = calendarUseCase.invoke(gregorianDate)
                _getHijriDateSuccess.value = respones
            } catch (eHttp: HttpException) {
                Log.d("ERROR", "Http Error : ${eHttp.message}")
            } catch (e: Exception) {
                Log.d("ERROR", "Http Error : ${e.message}")
            }
            _loadingData.value = false
        }
    }

    fun insertEvent(event: EventEntity) {
        viewModelScope.launch {
            repository.insert(event)
        }
    }
}
