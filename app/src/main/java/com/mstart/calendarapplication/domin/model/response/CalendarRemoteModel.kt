package com.mstart.calendarapplication.domin.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CalendarRemoteModel(
    @Json(name = "code")
    val code: Int?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "data")
    val data: DateResponseRemoteModel?,
)
