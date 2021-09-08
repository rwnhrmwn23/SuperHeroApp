package com.onedev.dicoding.superheroapp.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity

@Dao
interface SuperHeroDao {

    @Query("SELECT * FROM superHero WHERE id = :id")
    fun getSuperheroById(id: String): LiveData<HeroEntity>

    @Query("SELECT * FROM superHero WHERE name LIKE '%' || :name || '%'")
    fun getSuperHeroByName(name: String): LiveData<List<HeroEntity>>

    @Query("SELECT * FROM superHero WHERE isFavorite = 1")
    fun getFavoriteSuperHero(): LiveData<List<HeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSuperHero(heroEntities: List<HeroEntity>)

    @Update
    fun updateFavoriteSuperHero(heroEntity: HeroEntity)
}