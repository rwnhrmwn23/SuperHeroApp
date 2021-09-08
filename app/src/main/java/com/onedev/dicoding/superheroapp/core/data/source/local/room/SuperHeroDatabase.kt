package com.onedev.dicoding.superheroapp.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity

@Database(
    entities = [HeroEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SuperHeroDatabase : RoomDatabase() {

    abstract fun superHeroDao(): SuperHeroDao

    companion object {
        @Volatile
        private var INSTANCE: SuperHeroDatabase? = null

        fun getInstance(context: Context): SuperHeroDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuperHeroDatabase::class.java,
                    "SuperHero.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}