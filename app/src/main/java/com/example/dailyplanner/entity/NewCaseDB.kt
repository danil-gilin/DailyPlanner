package com.example.dailyplanner.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.sql.Timestamp

//класс для добавления новых дел в базу данных
data class NewCaseDB (
    @PrimaryKey
    @ColumnInfo(name = "Id")
     val id: Int?=null,
    @ColumnInfo(name = "date_start")
    val date_start: Timestamp,
    @ColumnInfo(name = "date_finish")
     val date_finish: Timestamp,
    @ColumnInfo(name = "name")
     val name: String,
    @ColumnInfo(name = "description")
     val description: String)