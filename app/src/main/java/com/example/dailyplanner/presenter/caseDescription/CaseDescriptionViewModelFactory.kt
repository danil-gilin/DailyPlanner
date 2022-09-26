package com.example.dailyplanner.presenter.caseDescription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CaseDescriptionViewModelFactory @Inject constructor(private val caseDescriptionViewModel: CaseDescriptionViewModel) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return caseDescriptionViewModel as T
    }
}