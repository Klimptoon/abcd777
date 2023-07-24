package com.example.abcd777.di

import com.example.abcd777.data.network.repository.CurrencyRepositoryImpl
import com.example.abcd777.data.network.responses.CourseApi
import com.example.abcd777.domain.repository.CurrencyRepository
import com.example.abcd777.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun providesCurrencyRepository(api: CourseApi): CurrencyRepository {
        return CurrencyRepositoryImpl(api = api)
    }

    @Provides
    fun providesCourseApi(): CourseApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).client(
                OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS).build()
            )
            .build()
            .create(CourseApi::class.java)
    }
}
