package com.mstart.calendarapplication.domin.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GregorianRemoteModel(
    @Json(name = "date")
    val date: String?,
    @Json(name = "day")
    val day: String?,
    @Json(name = "designation")
    val designation: DesignationRemoteModel?,
    @Json(name = "format")
    val format: String?,
    @Json(name = "month")
    val month: GregorianMonth?,
    @Json(name = "weekday")
    val weekday: GregorianWeekDayRemoteModel?,
    @Json(name = "year")
    val year: String?
)