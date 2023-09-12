package com.mstart.calendarapplication.domin.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GregorianMonth(
    @Json(name = "en")
    val en: String?,
    @Json(name = "number")
    val number: Int?
)