package com.onedev.dicoding.superheroapp.ui.fragment.detail

import androidx.lifecycle.ViewModel
import com.onedev.dicoding.superheroapp.core.data.SuperHeroRepository
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity

class DetailViewModel(private val superHeroRepository: SuperHeroRepository) : ViewModel() {

    fun getSuperheroById(id: String) = superHeroRepository.getSuperheroById(id)

    fun updateFavoriteSuperHero(heroEntity: HeroEntity, state: Boolean) = superHeroRepository.updateFavoriteSuperHero(heroEntity, state)

}