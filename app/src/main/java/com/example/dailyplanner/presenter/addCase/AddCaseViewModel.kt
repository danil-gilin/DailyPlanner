package com.example.dailyplanner.presenter.addCase

import android.util.Log
import androidx.collection.arrayMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyplanner.domain.AddDayCaseUseCase
import com.example.dailyplanner.entity.CaseDB
import com.example.dailyplanner.entity.NewCaseDB
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.sql.Timestamp
import javax.inject.Inject

class AddCaseViewModel @Inject constructor(private val addDayCaseUseCase: AddDayCaseUseCase) : ViewModel() {
    private val _state= MutableStateFlow<StateAddCase>(StateAddCase.Start)
    val state=_state.asStateFlow()


    fun addCase(name:String,descrption:String,timeStart:Long,timeEnd:Long,timeDate:Long){
        viewModelScope.launch {
            _state.value=StateAddCase.Loading

            //словарь для записи ошибок
            val errorMap= arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",true),Pair("Time",true))

            //проверка имени
            if(name==""){
                errorMap["NAME"]=false
            }

            //проверка описания
            if(descrption==""){
                errorMap["DESCRIPTION"]=false
            }

            //проверка временя (что время окончания не раньше времени начала)
            if (timeStart>timeEnd || timeStart==0L || timeEnd==0L ||timeDate==0L){
                errorMap["Time"]=false
            }

            //подведение итогов и смена состояние
            if (errorMap.values.all { it }){
                val case=NewCaseDB(null, Timestamp(timeDate+timeStart),Timestamp(timeDate+timeEnd),name,descrption)
                addDayCaseUseCase.addDayCase(case)
                _state.value=StateAddCase.Succses
            }else{
                _state.value=StateAddCase.Error(errorMap)
            }
        }
    }


}