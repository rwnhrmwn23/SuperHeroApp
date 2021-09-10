package com.onedev.dicoding.superheroapp.core.domain.usecase

import com.onedev.dicoding.superheroapp.core.domain.model.Hero
import com.onedev.dicoding.superheroapp.core.domain.repository.IHeroRepository

class HeroInteractor(private val heroRepository: IHeroRepository) : HeroUseCase {

    override fun getSuperheroById(id: String) = heroRepository.getSuperheroById(id)

    override fun getSuperHeroByName(name: String) = heroRepository.getSuperHeroByName(name)

    override fun getFavoriteSuperHero() = heroRepository.getFavoriteSuperHero()

    override fun updateFavoriteSuperHero(hero: Hero, state: Boolean) =
        heroRepository.updateFavoriteSuperHero(hero, state)
}