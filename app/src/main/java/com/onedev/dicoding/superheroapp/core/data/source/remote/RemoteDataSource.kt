package com.onedev.dicoding.superheroapp.core.data.source.remote

import android.util.Log
import com.onedev.dicoding.superheroapp.core.data.source.remote.network.ApiResponse
import com.onedev.dicoding.superheroapp.core.data.source.remote.network.ApiService
import com.onedev.dicoding.superheroapp.core.data.source.remote.response.HeroResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {
        private const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }

    suspend fun searchHeroByName(name: String): Flow<ApiResponse<List<HeroResults>>> {
        return flow {
            try {
                val response = apiService.getSuperHeroByName(name)
                val dataArray = response.results
                if (dataArray.isNotEmpty())
                    emit(ApiResponse.Success(dataArray))
                else
                    emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "searchHeroByName: $e" )
            }
        }.flowOn(Dispatchers.IO)
    }
}