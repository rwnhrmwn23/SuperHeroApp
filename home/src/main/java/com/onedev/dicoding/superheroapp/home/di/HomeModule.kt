package com.onedev.dicoding.superheroapp.home.di

import com.onedev.dicoding.superheroapp.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel { HomeViewModel(get()) }
}