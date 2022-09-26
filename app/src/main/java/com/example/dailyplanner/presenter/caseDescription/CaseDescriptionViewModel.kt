package com.example.dailyplanner.presenter.caseDescription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyplanner.domain.GetCaseFullUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class CaseDescriptionViewModel @Inject constructor(private val getCaseFullUseCase: GetCaseFullUseCase): ViewModel() {
    private val _state= MutableStateFlow<StateDescription>(StateDescription.Loading)
    val state=_state.asStateFlow()

    //запрос дела по id
    fun getCase(id:Int) {
        viewModelScope.launch {
            _state.value=StateDescription.Loading
            try {
                val case = getCaseFullUseCase.getCaseFull(id)
                _state.value=StateDescription.Succses(case)
            }catch (e:Exception){
                _state.value=StateDescription.Error("error receiving the task")
            }
        }
    }

}