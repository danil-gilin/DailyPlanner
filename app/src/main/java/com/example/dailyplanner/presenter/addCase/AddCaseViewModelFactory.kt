package com.example.dailyplanner.presenter.addCase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class AddCaseViewModelFactory@Inject constructor(private val addCaseViewModel: AddCaseViewModel):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return addCaseViewModel as T
    }
}