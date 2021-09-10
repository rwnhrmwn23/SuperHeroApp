package com.onedev.dicoding.superheroapp.core.data.source.local.room

import androidx.room.*
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SuperHeroDao {

    @Query("SELECT * FROM superHero WHERE id = :id")
    fun getSuperheroById(id: String): Flow<HeroEntity>

    @Query("SELECT * FROM superHero WHERE name LIKE '%' || :name || '%'")
    fun getSuperHeroByName(name: String): Flow<List<HeroEntity>>

    @Query("SELECT * FROM superHero WHERE isFavorite = 1")
    fun getFavoriteSuperHero(): Flow<List<HeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperHero(heroEntities: List<HeroEntity>)

    @Update
    fun updateFavoriteSuperHero(heroEntity: HeroEntity)
}