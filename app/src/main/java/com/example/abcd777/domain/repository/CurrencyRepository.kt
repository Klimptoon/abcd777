package com.example.abcd777.domain.repository

import com.example.abcd777.domain.models.Currency

interface CurrencyRepository {
    suspend fun getCurrency(city: String) : Currency
}