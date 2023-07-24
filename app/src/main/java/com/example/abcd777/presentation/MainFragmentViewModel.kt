package com.example.abcd777.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abcd777.domain.models.Currency
import com.example.abcd777.domain.usecases.GetCurrencyUseCase
import com.example.abcd777.util.Resource
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getCurrencyUseCase: GetCurrencyUseCase
) : ViewModel() {

    var position = 0
    var city = MutableLiveData<String>()

    private var _currency = MutableLiveData<Currency?>()         //лайв даты для работы приложения
    var currency: LiveData<Currency?> = _currency

    private var _isConnected = MutableLiveData<Boolean>()
    var isConnected: LiveData<Boolean> = _isConnected

    private var _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    fun getCurrency(city: String) {                               //Метод для получения курса валют для любого города
        viewModelScope.launch {
            _isConnected.postValue(true)
            _loading.postValue(true)
            when (val currency = getCurrencyUseCase.execute(city = city)) {
                is Resource.Success -> {
                    _loading.postValue(false)
                    _currency.postValue(currency.data)

                }
                is Resource.Error -> {
                    _isConnected.postValue(false)
                    _loading.postValue(false)
                }
            }
        }
    }

    fun getDefaultCurrency() {                                      //Метод для получения курса валют для первого открытия приложения
        viewModelScope.launch {
            _isConnected.postValue(true)
            _loading.postValue(true)
            when (val currency = getCurrencyUseCase.execute("Брест")) {
                is Resource.Success -> {
                    _loading.postValue(false)
                    _currency.postValue(currency.data)

                }
                is Resource.Error -> {
                    _isConnected.postValue(false)
                    _loading.postValue(false)
                }
            }
        }
    }

}
