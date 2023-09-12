package com.mstart.calendarapplication.domin.repository

import com.mstart.calendarapplication.data.datasource.ApiDataSource
import com.mstart.calendarapplication.domin.model.response.DateResponseRemoteModel
import javax.inject.Inject

class CalendarRepository @Inject constructor(private val apiService: ApiDataSource) {

    suspend fun getData(gregorianDate: String): DateResponseRemoteModel? {
        return apiService.getDate(gregorianDate).data
    }
}
