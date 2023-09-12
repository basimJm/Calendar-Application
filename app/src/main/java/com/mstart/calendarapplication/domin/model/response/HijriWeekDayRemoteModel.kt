package com.mstart.calendarapplication.domin.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HijriWeekDayRemoteModel(
    @Json(name = "ar")
    val ar: String?,
    @Json(name = "en")
    val en: String?
)