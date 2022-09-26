package com.example.dailyplanner.entity

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import java.sql.Timestamp

//интерфейс дел
interface CaseInt{
val id: Int?
val date_start: Timestamp
val date_finish: Timestamp
val name: String
val description: String
}