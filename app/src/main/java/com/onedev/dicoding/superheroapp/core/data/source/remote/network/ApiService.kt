package com.onedev.dicoding.superheroapp.core.data.source.remote.network

import com.onedev.dicoding.superheroapp.core.data.source.remote.response.HeroResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("search/{name}")
    suspend fun getSuperHeroByName(
        @Path("name") name: String
    ): HeroResponse

}