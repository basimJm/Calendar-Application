package com.mstart.calendarapplication.domin.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DesignationRemoteModel(
    @Json(name = "abbreviated")
    val abbreviated: String?,
    @Json(name = "expanded")
    val expanded: String?,
)