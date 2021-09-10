package com.onedev.dicoding.superheroapp.di

import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroInteractor
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<HeroUseCase> { HeroInteractor(get()) }
}