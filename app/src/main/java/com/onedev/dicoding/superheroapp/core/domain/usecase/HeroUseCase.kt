package com.onedev.dicoding.superheroapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.onedev.dicoding.superheroapp.core.data.Resource
import com.onedev.dicoding.superheroapp.core.domain.model.Hero

interface HeroUseCase {
    fun getSuperheroById(id: String): LiveData<Hero>
    fun getSuperHeroByName(name: String): LiveData<Resource<List<Hero>>>
    fun getFavoriteSuperHero(): LiveData<List<Hero>>
    fun updateFavoriteSuperHero(hero: Hero, state: Boolean)
}