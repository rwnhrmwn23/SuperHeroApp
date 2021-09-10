package com.onedev.dicoding.superheroapp.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superHero")
data class HeroEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val eyeColor: String,
    val gender: String,
    val hairColor: String,
    val race: String,
    val height: String,
    val weight: String,
    val alignment: String,
    val alterEgos: String,
    val firstAppearance: String,
    val fullName: String,
    val placeOfBirth: String,
    val publisher: String,
    val groupAffiliation: String,
    val relatives: String,
    val url: String,
    val combat: String,
    val durability: String,
    val intelligence: String,
    val power: String,
    val speed: String,
    val strength: String,
    val base: String,
    val occupation: String,
    var isFavorite: Boolean = false
)