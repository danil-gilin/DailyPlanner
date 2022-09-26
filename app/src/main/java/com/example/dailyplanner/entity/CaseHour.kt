package com.example.dailyplanner.entity

//класс для массива сортировки дел по часам и отправки в recycleView
data class CaseHour (
    val hour:Long,
    val cases:ArrayList<CaseInt>
)