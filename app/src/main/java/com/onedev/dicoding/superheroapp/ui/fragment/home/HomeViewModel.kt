package com.onedev.dicoding.superheroapp.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.onedev.dicoding.superheroapp.core.data.Resource
import com.onedev.dicoding.superheroapp.core.data.SuperHeroRepository
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity

class HomeViewModel(private val superHeroRepository: SuperHeroRepository) : ViewModel() {

    fun getSuperHeroByName(name: String): LiveData<Resource<List<HeroEntity>>> = superHeroRepository.getSuperHeroByName(name)

    fun getFavoriteSuperHero(): LiveData<List<HeroEntity>> = superHeroRepository.getFavoriteSuperHero()

}