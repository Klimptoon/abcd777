package com.example.abcd777.data.network.responses

import com.example.abcd777.data.network.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CourseApi {
    @GET("kursExchange?city={city}")
    suspend fun getCountyCurrency(@Path("city") city: String) : CurrencyDto
}