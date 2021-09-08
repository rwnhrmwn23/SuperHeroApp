package com.onedev.dicoding.superheroapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.onedev.dicoding.superheroapp.core.data.source.local.LocalDataSource
import com.onedev.dicoding.superheroapp.core.data.source.remote.RemoteDataSource
import com.onedev.dicoding.superheroapp.core.data.source.remote.network.ApiResponse
import com.onedev.dicoding.superheroapp.core.data.source.remote.response.HeroResults
import com.onedev.dicoding.superheroapp.core.domain.model.Hero
import com.onedev.dicoding.superheroapp.core.domain.repository.IHeroRepository
import com.onedev.dicoding.superheroapp.core.utils.AppExecutors
import com.onedev.dicoding.superheroapp.core.utils.DataMapper

class HeroRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IHeroRepository {

    companion object {
        @Volatile
        private var instance: HeroRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): HeroRepository =
            instance ?: synchronized(this) {
                instance ?: HeroRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getSuperHeroByName(name: String): LiveData<Resource<List<Hero>>> =
        object : NetworkBoundResource<List<Hero>, List<HeroResults>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Hero>> {
                return Transformations.map(localDataSource.getSuperHeroByName(name)) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Hero>?): Boolean {
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


    override fun getSuperheroById(id: String): LiveData<Hero> {
        return Transformations.map(localDataSource.getSuperheroById(id)) {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override fun getFavoriteSuperHero(): LiveData<List<Hero>> {
        return Transformations.map(localDataSource.getFavoriteSuperHero()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun updateFavoriteSuperHero(hero: Hero, state: Boolean) {
        val heroEntity = DataMapper.mapDomainToEntity(hero)
        appExecutors.diskIO().execute {
            localDataSource.updateFavoriteSuperHero(heroEntity, state)
        }
    }
}