package com.onedev.dicoding.superheroapp.core.domain.usecase

import com.onedev.dicoding.superheroapp.core.data.Resource
import com.onedev.dicoding.superheroapp.core.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface HeroUseCase {
    fun getSuperheroById(id: String): Flow<Hero>
    fun getSuperHeroByName(name: String): Flow<Resource<List<Hero>>>
    fun getFavoriteSuperHero(): Flow<List<Hero>>
    fun updateFavoriteSuperHero(hero: Hero, state: Boolean)
}