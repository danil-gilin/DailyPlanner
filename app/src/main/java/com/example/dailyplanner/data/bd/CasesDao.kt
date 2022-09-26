package com.example.dailyplanner.data.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dailyplanner.entity.Case
import com.example.dailyplanner.entity.CaseDB
import com.example.dailyplanner.entity.NewCaseDB
import java.sql.Timestamp

@Dao
interface CasesDao {
    //Запрос списка всех дел на данный день (day:Timestamp)
    @Query("SELECT * FROM CaseDB Where :day<date_start AND date_start<:day+86400000 ")
    suspend fun takeDayCases(day: Timestamp): List<CaseDB>

    //Запрос конткретного дела по id
    @Query("SELECT * FROM CaseDB Where id=:id")
    suspend fun takeFullInfoCase(id: Int): CaseDB

    //Добавление нового дела
    @Insert(entity = CaseDB::class)
    suspend fun addDayCases(dayCases: NewCaseDB)
}