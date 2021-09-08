package com.onedev.dicoding.superheroapp.core.di

import android.content.Context
import com.onedev.dicoding.superheroapp.core.data.HeroRepository
import com.onedev.dicoding.superheroapp.core.data.source.local.LocalDataSource
import com.onedev.dicoding.superheroapp.core.data.source.local.room.SuperHeroDatabase
import com.onedev.dicoding.superheroapp.core.data.source.remote.RemoteDataSource
import com.onedev.dicoding.superheroapp.core.data.source.remote.network.ApiConfig
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroInteractor
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroUseCase
import com.onedev.dicoding.superheroapp.core.utils.AppExecutors


object Injection {
    fun provideRepository(context: Context): HeroRepository {
        val database = SuperHeroDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.superHeroDao())
        val appExecutors = AppExecutors()

        return HeroRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideHeroUseCase(context: Context): HeroUseCase {
        val repository = provideRepository(context)
        return HeroInteractor(repository)
    }
}
