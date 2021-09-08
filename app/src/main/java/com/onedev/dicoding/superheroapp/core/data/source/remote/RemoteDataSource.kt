package com.onedev.dicoding.superheroapp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity
import com.onedev.dicoding.superheroapp.core.data.source.remote.network.ApiResponse
import com.onedev.dicoding.superheroapp.core.data.source.remote.network.ApiService
import com.onedev.dicoding.superheroapp.core.data.source.remote.response.HeroResponse
import com.onedev.dicoding.superheroapp.core.data.source.remote.response.HeroResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService){

    companion object {
        private const val TAG = "RemoteDataSource"
        @Volatile

        private var instance: RemoteDataSource?  = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }

    fun searchHeroByName(name: String): LiveData<ApiResponse<List<HeroResults>>> {
        val resultData = MutableLiveData<ApiResponse<List<HeroResults>>>()
        val client = apiService.getSuperHeroByName(name)
        client.enqueue(object : Callback<HeroResponse> {
            override fun onResponse(call: Call<HeroResponse>, response: Response<HeroResponse>) {
                val dataArray = response.body()?.results
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<HeroResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
        return resultData
    }

}