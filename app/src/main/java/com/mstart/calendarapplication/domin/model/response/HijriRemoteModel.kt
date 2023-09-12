package com.mstart.calendarapplication.domin.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HijriRemoteModel(
    @Json(name = "date")
    val date: String?,
    @Json(name = "day")
    val day: String?,
    @Json(name = "designation")
    val designation: DesignationRemoteModel?,
    @Json(name = "format")
    val format: String?,
    @Json(name = "holidays")
    val holidays: List<Any>?,
    @Json(name = "month")
    val month: HijriMonthRemoteModel?,
    @Json(name = "weekday")
    val weekday: HijriWeekDayRemoteModel?,
    @Json(name = "year")
    val year: String?
)