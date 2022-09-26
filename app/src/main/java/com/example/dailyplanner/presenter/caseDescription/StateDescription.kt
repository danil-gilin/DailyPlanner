package com.example.dailyplanner.presenter.caseDescription

import com.example.dailyplanner.entity.CaseDB
import com.example.dailyplanner.entity.CaseHour

sealed interface StateDescription {
    data class Succses(val case:CaseDB ): StateDescription//успех
    object Loading: StateDescription//загрузка
    data class Error(val error:String): StateDescription//ошибка
}