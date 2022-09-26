package com.example.dailyplanner.data.bd

import androidx.room.TypeConverter
import com.example.dailyplanner.entity.Case
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import java.sql.Timestamp

//Конвертеры для преобразования Timestamp в Long и обратно
object Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Timestamp? {
        return value?.let { Timestamp(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Timestamp?): Long? {
        return date?.time
    }
}