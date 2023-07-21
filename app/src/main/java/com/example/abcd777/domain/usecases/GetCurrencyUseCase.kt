package com.example.abcd777.domain.usecases

import com.example.abcd777.domain.models.Currency
import com.example.abcd777.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend fun execute(city: String) : Currency {
        return repository.getCurrency(city = city)
    }
}