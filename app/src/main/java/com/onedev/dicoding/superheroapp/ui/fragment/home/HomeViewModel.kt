package com.onedev.dicoding.superheroapp.ui.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroUseCase

class HomeViewModel(private val heroUseCase: HeroUseCase) : ViewModel() {

    fun getSuperHeroByName(name: String) = heroUseCase.getSuperHeroByName(name).asLiveData()

}