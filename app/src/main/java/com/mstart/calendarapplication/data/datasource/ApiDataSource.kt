package com.mstart.calendarapplication.data.datasource

import com.mstart.calendarapplication.domin.model.response.CalendarRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDataSource {

    @GET("v1/gToH/{date}")
    suspend fun getDate(@Path("date") gregorianDate: String): CalendarRemoteModel
}