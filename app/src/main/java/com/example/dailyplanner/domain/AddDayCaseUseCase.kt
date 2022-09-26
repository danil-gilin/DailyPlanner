package com.example.dailyplanner.domain

import com.example.dailyplanner.data.RepositoryCalendar
import com.example.dailyplanner.entity.CaseDB
import com.example.dailyplanner.entity.CaseInt
import com.example.dailyplanner.entity.NewCaseDB
import javax.inject.Inject

class AddDayCaseUseCase @Inject constructor (private val repositoryCalendar: RepositoryCalendar){
    //Добавление нового дела
   suspend fun addDayCase(case:NewCaseDB){
        repositoryCalendar.addDayCase(case)
    }
}