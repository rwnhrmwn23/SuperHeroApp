package com.onedev.dicoding.superheroapp

import android.app.Application
import com.onedev.dicoding.superheroapp.core.di.databaseModule
import com.onedev.dicoding.superheroapp.core.di.networkModule
import com.onedev.dicoding.superheroapp.core.di.repositoryModule
import com.onedev.dicoding.superheroapp.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                )
            )
        }
    }

}