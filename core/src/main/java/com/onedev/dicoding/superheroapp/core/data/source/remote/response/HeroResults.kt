package com.onedev.dicoding.superheroapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class HeroResults(
    val appearance: HeroAppearance,
    val biography: HeroBiography,
    val connections: HeroConnections,
    val id: String,
    val image: HeroImage,
    val name: String,
    @SerializedName("powerstats")
    val powerStats: HeroPowerStats,
    val response: String,
    val work: HeroWork
)