package com.example.dailyplanner.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.sql.Timestamp

//Json класс дел
@JsonClass(generateAdapter = true)
data class Case (
    @Json(name = "Id")
    override val id: Int,
    @Json(name = "date_start")
    override val date_start: Timestamp,
    @Json(name = "date_finish")
    override val date_finish: Timestamp,
    @Json(name = "name")
    override val name: String,
    @Json(name = "description")
    override val description: String
    ):CaseInt