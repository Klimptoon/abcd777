package com.example.abcd777.domain.usecases

import com.example.abcd777.domain.models.Currency
import com.example.abcd777.domain.repository.CurrencyRepository
import com.example.abcd777.util.Constants.ERROR_MESSAGE
import com.example.abcd777.util.Resource
import java.lang.Exception
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend fun execute(city: String): Resource<Currency> {
        val currency = try {
            repository.getCurrency(city = city)
        } catch (e: Exception) {
            return Resource.Error(ERROR_MESSAGE)
        }
        return Resource.Success(currency)
    }
}
