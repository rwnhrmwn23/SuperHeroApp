package com.onedev.dicoding.superheroapp.core.di

import androidx.room.Room
import com.onedev.dicoding.superheroapp.core.data.HeroRepository
import com.onedev.dicoding.superheroapp.core.data.source.local.LocalDataSource
import com.onedev.dicoding.superheroapp.core.data.source.local.room.SuperHeroDatabase
import com.onedev.dicoding.superheroapp.core.data.source.remote.RemoteDataSource
import com.onedev.dicoding.superheroapp.core.data.source.remote.network.ApiService
import com.onedev.dicoding.superheroapp.core.domain.repository.IHeroRepository
import com.onedev.dicoding.superheroapp.core.utils.AppExecutors
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.ACCESS_TOKEN
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.DATABASE_NAME
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.DATABASE_PASSWORD
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.HOSTNAME
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes(DATABASE_PASSWORD.toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidContext(), SuperHeroDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = HOSTNAME
        val certificatePin = CertificatePinner.Builder()
            .add(hostname, "sha256/c4ytsPxnTm2Sg9NNAMRISRz/3C43Z192m0XZUmCOhxw=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .certificatePinner(certificatePin)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/api/${ACCESS_TOKEN}/")
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
    single<IHeroRepository> {
        HeroRepository(get(), get(), get())
    }
}