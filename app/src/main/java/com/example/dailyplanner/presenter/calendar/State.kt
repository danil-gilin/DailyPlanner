package com.example.dailyplanner.presenter.calendar

import com.example.dailyplanner.entity.CaseDB
import com.example.dailyplanner.entity.CaseHour

sealed interface State{
    data class Succses(val dayCases:List<CaseHour> ):State//успех
    object Loading:State//загрузка
    data class Error(val error:String):State//ошибка
}