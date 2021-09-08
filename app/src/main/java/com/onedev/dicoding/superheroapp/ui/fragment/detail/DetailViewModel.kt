package com.onedev.dicoding.superheroapp.ui.fragment.detail

import androidx.lifecycle.ViewModel
import com.onedev.dicoding.superheroapp.core.data.HeroRepository
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity
import com.onedev.dicoding.superheroapp.core.domain.model.Hero
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroUseCase

class DetailViewModel(private val heroUseCase: HeroUseCase) : ViewModel() {

    fun getSuperheroById(id: String) = heroUseCase.getSuperheroById(id)

    fun updateFavoriteSuperHero(hero: Hero, state: Boolean) = heroUseCase.updateFavoriteSuperHero(hero, state)

}