package com.onedev.dicoding.superheroapp.core.data

import com.onedev.dicoding.superheroapp.core.data.source.local.LocalDataSource
import com.onedev.dicoding.superheroapp.core.data.source.remote.RemoteDataSource
import com.onedev.dicoding.superheroapp.core.data.source.remote.network.ApiResponse
import com.onedev.dicoding.superheroapp.core.data.source.remote.response.HeroResults
import com.onedev.dicoding.superheroapp.core.domain.model.Hero
import com.onedev.dicoding.superheroapp.core.domain.repository.IHeroRepository
import com.onedev.dicoding.superheroapp.core.utils.AppExecutors
import com.onedev.dicoding.superheroapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HeroRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IHeroRepository {

    override fun getSuperHeroByName(name: String): Flow<Resource<List<Hero>>> =
        object : com.onedev.dicoding.superheroapp.core.data.NetworkBoundResource<List<Hero>, List<HeroResults>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Hero>> {
                return localDataSource.getSuperHeroByName(name)
                    .map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Hero>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<HeroResults>>> {
                return remoteDataSource.searchHeroByName(name)
            }

            override suspend fun saveCallResult(data: List<HeroResults>) {
                val superHeroList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSuperHero(superHeroList)
            }
        }.asFlow()


    override fun getSuperheroById(id: String): Flow<Hero> {
        return localDataSource.getSuperheroById(id).map { DataMapper.mapEntityToDomain(it) }
    }

    override fun getFavoriteSuperHero(): Flow<List<Hero>> {
        return localDataSource.getFavoriteSuperHero().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun updateFavoriteSuperHero(hero: Hero, state: Boolean) {
        val heroEntity = DataMapper.mapDomainToEntity(hero)
        appExecutors.diskIO().execute {
            localDataSource.updateFavoriteSuperHero(heroEntity, state)
        }
    }
}