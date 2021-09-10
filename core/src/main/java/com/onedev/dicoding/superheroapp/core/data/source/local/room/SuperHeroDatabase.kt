package com.onedev.dicoding.superheroapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity

@Database(entities = [HeroEntity::class], version = 1, exportSchema = false)
abstract class SuperHeroDatabase : RoomDatabase() {

    abstract fun superHeroDao(): SuperHeroDao

}