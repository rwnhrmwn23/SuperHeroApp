package com.onedev.dicoding.superheroapp.fragment.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroUseCase

class FavoriteViewModel(heroUseCase: HeroUseCase) : ViewModel() {

    val getFavoriteSuperHero = heroUseCase.getFavoriteSuperHero().asLiveData()

}