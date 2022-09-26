package com.example.dailyplanner.presenter.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyplanner.domain.AddDayCaseUseCase
import com.example.dailyplanner.domain.GetListCaseUseCase
import com.example.dailyplanner.entity.CaseDB
import com.example.dailyplanner.entity.Constance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.text.SimpleDateFormat
import javax.inject.Inject

class MainViewModel @Inject constructor (private val getListCaseUseCase: GetListCaseUseCase,private val addDayCaseUseCase: AddDayCaseUseCase): ViewModel() {
    private val _state= MutableStateFlow<State>(State.Succses(emptyList()))
    val state=_state.asStateFlow()

    fun getDayCases(year:Int,month:Int,day:Int){
        viewModelScope.launch {
            _state.value=State.Loading
            //подготовка данных для отправки запроса
            val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(Constance.SIMPLE_DATE_FROMAT)
                val date= simpleDateFormat.parse("$day/${month+1}/$year")
            try {
                //отправка запроса на получение сортировонаго по часам списка на конкретный день
                val listCases = getListCaseUseCase.getListCase(Timestamp(date.time))
                _state.value = State.Succses(listCases)
            }catch (e:Exception){
                _state.value = State.Error("error")
            }
        }
    }
}