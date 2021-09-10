package com.onedev.dicoding.superheroapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.onedev.dicoding.superheroapp.core.domain.model.Hero
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroUseCase

class DetailViewModel(private val heroUseCase: HeroUseCase) : ViewModel() {

    fun getSuperheroById(id: String) = heroUseCase.getSuperheroById(id).asLiveData()

    fun updateFavoriteSuperHero(hero: Hero, state: Boolean) =
        heroUseCase.updateFavoriteSuperHero(hero, state)

}