package com.onedev.dicoding.superheroapp.ui.fragment.favorite

import androidx.lifecycle.ViewModel
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroUseCase

class FavoriteViewModel(heroUseCase: HeroUseCase) : ViewModel() {

    val getFavoriteSuperHero = heroUseCase.getFavoriteSuperHero()

}