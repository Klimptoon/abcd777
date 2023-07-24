package com.example.abcd777.data.network.responses


import com.example.abcd777.data.network.dto.ForApiItem
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseApi {
    @GET("kursExchange")
    suspend fun getCountyCurrency(@Query("city") city: String): List<ForApiItem>
}
