package com.example.abcd777.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.abcd777.domain.usecases.GetCurrencyUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    val getCurrencyUseCase: GetCurrencyUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(getCurrencyUseCase = getCurrencyUseCase) as T
    }
}
