package com.onedev.dicoding.superheroapp.ui.fragment.home

import androidx.lifecycle.ViewModel
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroUseCase

class HomeViewModel(private val heroUseCase: HeroUseCase) : ViewModel() {

    fun getSuperHeroByName(name: String) = heroUseCase.getSuperHeroByName(name)

}