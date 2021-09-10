package com.onedev.dicoding.superheroapp.detail.di

import com.onedev.dicoding.superheroapp.detail.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailViewModelModule = module {
    viewModel { DetailViewModel(get()) }
}