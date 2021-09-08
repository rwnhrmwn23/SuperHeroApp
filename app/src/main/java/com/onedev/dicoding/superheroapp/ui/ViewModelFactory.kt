package com.onedev.dicoding.superheroapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onedev.dicoding.superheroapp.core.di.Injection
import com.onedev.dicoding.superheroapp.core.domain.usecase.HeroUseCase
import com.onedev.dicoding.superheroapp.ui.fragment.detail.DetailViewModel
import com.onedev.dicoding.superheroapp.ui.fragment.favorite.FavoriteViewModel
import com.onedev.dicoding.superheroapp.ui.fragment.home.HomeViewModel

class ViewModelFactory private constructor(private val heroUseCase: HeroUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideHeroUseCase(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(heroUseCase) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(heroUseCase) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(heroUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

}