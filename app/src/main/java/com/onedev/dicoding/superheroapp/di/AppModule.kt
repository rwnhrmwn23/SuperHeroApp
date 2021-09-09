package com.onedev.dicoding.superheroapp.di

import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroInteractor
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroUseCase
import com.onedev.dicoding.superheroapp.fragment.detail.DetailViewModel
import com.onedev.dicoding.superheroapp.fragment.favorite.FavoriteViewModel
import com.onedev.dicoding.superheroapp.fragment.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<HeroUseCase> { HeroInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}