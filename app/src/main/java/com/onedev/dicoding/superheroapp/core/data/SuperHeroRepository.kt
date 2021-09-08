package com.onedev.dicoding.superheroapp.core.data

import androidx.lifecycle.LiveData
import com.onedev.dicoding.superheroapp.core.data.source.local.LocalDataSource
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity
import com.onedev.dicoding.superheroapp.core.data.source.remote.RemoteDataSource
import com.onedev.dicoding.superheroapp.core.data.source.remote.network.ApiResponse
import com.onedev.dicoding.superheroapp.core.data.source.remote.response.HeroResults
import com.onedev.dicoding.superheroapp.core.utils.AppExecutors
import com.onedev.dicoding.superheroapp.core.utils.DataMapper

class SuperHeroRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    companion object {
        @Volatile
        private var instance: SuperHeroRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): SuperHeroRepository =
            instance ?: synchronized(this) {
                instance ?: SuperHeroRepository(remoteData, localData, appExecutors)
            }
    }

    fun getSuperHeroByName(name: String): LiveData<Resource<List<HeroEntity>>> =
        object : NetworkBoundResource<List<HeroEntity>, List<HeroResults>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<HeroEntity>> {
                return localDataSource.getSuperHeroByName(name)
            }

            override fun shouldFetch(data: List<HeroEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<HeroResults>>> {
                return remoteDataSource.searchHeroByName(name)
            }

            override fun saveCallResult(data: List<HeroResults>) {
                val superHeroList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSuperHero(superHeroList)
            }
        }.asLiveData()


    fun getSuperheroById(id: String): LiveData<HeroEntity> {
        return localDataSource.getSuperheroById(id)
    }

    fun getFavoriteSuperHero(): LiveData<List<HeroEntity>> {
        return localDataSource.getFavoriteSuperHero()
    }

    fun updateFavoriteSuperHero(heroEntity: HeroEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.updateFavoriteSuperHero(heroEntity, state)
        }
    }
}