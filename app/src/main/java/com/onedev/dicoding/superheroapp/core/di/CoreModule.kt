package com.onedev.dicoding.superheroapp.core.di

import androidx.room.Room
import com.onedev.dicoding.superheroapp.BuildConfig
import com.onedev.dicoding.superheroapp.core.data.HeroRepository
import com.onedev.dicoding.superheroapp.core.data.source.local.LocalDataSource
import com.onedev.dicoding.superheroapp.core.data.source.local.room.SuperHeroDatabase
import com.onedev.dicoding.superheroapp.core.data.source.remote.RemoteDataSource
import com.onedev.dicoding.superheroapp.core.data.source.remote.network.ApiService
import com.onedev.dicoding.superheroapp.core.domain.repository.IHeroRepository
import com.onedev.dicoding.superheroapp.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<SuperHeroDatabase>().superHeroDao() }
    single {
        Room.databaseBuilder(androidContext(), SuperHeroDatabase::class.java, "SuperHero.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/api.php/${BuildConfig.ACCESS_TOKEN}/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IHeroRepository> { HeroRepository(get(), get(), get()) }
}