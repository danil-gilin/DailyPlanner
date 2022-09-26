package com.example.dailyplanner.presenter.addCase

import com.example.dailyplanner.entity.CaseHour
import com.example.dailyplanner.presenter.calendar.State

sealed interface StateAddCase{
    object Succses: StateAddCase//удачно
    object Start: StateAddCase//начало
    object Loading: StateAddCase//обработка
    data class Error(val error:Map<String,Boolean>): StateAddCase//ошибка
}