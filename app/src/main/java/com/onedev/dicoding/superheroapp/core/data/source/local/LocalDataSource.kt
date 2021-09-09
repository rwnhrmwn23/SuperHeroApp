package com.onedev.dicoding.superheroapp.core.data.source.local

import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity
import com.onedev.dicoding.superheroapp.core.data.source.local.room.SuperHeroDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val superHeroDao: SuperHeroDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(superHeroDao: SuperHeroDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(superHeroDao)
            }
    }

    fun getSuperheroById(id: String): Flow<HeroEntity> = superHeroDao.getSuperheroById(id)

    fun getSuperHeroByName(name: String): Flow<List<HeroEntity>> = superHeroDao.getSuperHeroByName(name)

    fun getFavoriteSuperHero(): Flow<List<HeroEntity>> = superHeroDao.getFavoriteSuperHero()

    suspend fun insertSuperHero(heroEntities: List<HeroEntity>) = superHeroDao.insertSuperHero(heroEntities)

    fun updateFavoriteSuperHero(heroEntity: HeroEntity, newState: Boolean) {
        heroEntity.isFavorite = newState
        superHeroDao.updateFavoriteSuperHero(heroEntity)
    }
}