package com.onedev.dicoding.superheroapp.core.data.source.local

import androidx.lifecycle.LiveData
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity
import com.onedev.dicoding.superheroapp.core.data.source.local.room.SuperHeroDao

class LocalDataSource private constructor(private val superHeroDao: SuperHeroDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(superHeroDao: SuperHeroDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(superHeroDao)
            }
    }

    fun getSuperheroById(id: String): LiveData<HeroEntity> = superHeroDao.getSuperheroById(id)

    fun getSuperHeroByName(name: String): LiveData<List<HeroEntity>> = superHeroDao.getSuperHeroByName(name)

    fun getFavoriteSuperHero(): LiveData<List<HeroEntity>> = superHeroDao.getFavoriteSuperHero()

    fun insertSuperHero(heroEntities: List<HeroEntity>) = superHeroDao.insertSuperHero(heroEntities)

    fun updateFavoriteSuperHero(heroEntity: HeroEntity, newState: Boolean) {
        heroEntity.isFavorite = newState
        superHeroDao.updateFavoriteSuperHero(heroEntity)
    }
}