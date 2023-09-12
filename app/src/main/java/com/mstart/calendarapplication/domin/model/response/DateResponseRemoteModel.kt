package com.mstart.calendarapplication.domin.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DateResponseRemoteModel(
    @Json(name = "gregorian")
    val gregorian: GregorianRemoteModel?,
    @Json(name = "hijri")
    val hijri: HijriRemoteModel?,
)