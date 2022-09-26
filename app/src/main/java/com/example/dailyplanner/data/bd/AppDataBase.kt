package com.example.dailyplanner.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.dailyplanner.entity.CaseDB

@Database(entities =[CaseDB::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase(){
    abstract fun photoDao():CasesDao  //метод для создания экзампляра базы данных
}