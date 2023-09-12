package com.mstart.calendarapplication.domin.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GregorianWeekDayRemoteModel(
    @Json(name = "en")
    val en: String?
)