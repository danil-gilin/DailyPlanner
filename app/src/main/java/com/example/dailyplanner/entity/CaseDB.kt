package com.example.dailyplanner.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.sql.Timestamp

//Таблица бд  дел
@Entity(tableName = "CaseDB")
data class CaseDB(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    override val id: Int?=null,
    @ColumnInfo(name = "date_start")
    override val date_start: Timestamp,
    @ColumnInfo(name = "date_finish")
    override val date_finish: Timestamp,
    @ColumnInfo(name = "name")
    override val name: String,
    @ColumnInfo(name = "description")
    override val description: String
):CaseInt