package com.onedev.dicoding.superheroapp.core.data.source.remote.network

import com.onedev.dicoding.superheroapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    fun provideApiService() : ApiService {
        val retrofit  = Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/api.php/${BuildConfig.ACCESS_TOKEN}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

}