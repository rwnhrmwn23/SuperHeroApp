package com.onedev.dicoding.superheroapp.favorite.di

import com.onedev.dicoding.superheroapp.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteViewModelModule = module {
    viewModel { FavoriteViewModel(get()) }
}