package com.example.abcd777.data.network.repository

import com.example.abcd777.data.network.dto.toCurrency
import com.example.abcd777.data.network.responses.CourseApi
import com.example.abcd777.domain.models.Currency
import com.example.abcd777.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CourseApi
) : CurrencyRepository {
    override suspend fun getCurrency(city: String): Currency {
        return api.getCountyCurrency(city = city).toCurrency()
    }
}