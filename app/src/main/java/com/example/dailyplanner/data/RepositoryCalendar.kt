package com.example.dailyplanner.data

import android.util.Log
import com.example.dailyplanner.data.bd.CasesDao
import com.example.dailyplanner.entity.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RepositoryCalendar @Inject constructor(private val casesDao: CasesDao) {

    //Запрос списка всех дел на данный день (day:Timestamp)
    suspend fun getListCase(day: Timestamp): List<CaseHour> {

        //заполняем список отсортированных по часам дел пустыми делами
        var listHour = arrayListOf<CaseHour>()
        for (hour in 0..23) {
            listHour.add(CaseHour(hour * 3600000L, arrayListOf<CaseInt>()))
        }

        //заполняем отсортированный список делами по условию
        casesDao.takeDayCases(day).forEach { case ->
            listHour.forEach { hour ->
                val time = SimpleDateFormat(
                    Constance.SIMPLE_HOUR_FROMAT,
                    Locale.UK
                ).format(case.date_start)
                val longTime = SimpleDateFormat(Constance.SIMPLE_HOUR_FROMAT, Locale.UK).parse(time)
                //проверка в какой час входит дело
                if (longTime.time >= hour.hour && longTime.time < hour.hour + Constance.HOUR) {
                    hour.cases.add(case)
                }
            }
        }

        //возвращаем список дел
        return listHour
    }

    //запрос в базе данных определенного дела
    suspend fun getCaseFull(id: Int): CaseDB {
        return casesDao.takeFullInfoCase(id)
    }

    //Добавление нового дела
    suspend fun addDayCase(case: NewCaseDB) {
        casesDao.addDayCases(case)
    }
}