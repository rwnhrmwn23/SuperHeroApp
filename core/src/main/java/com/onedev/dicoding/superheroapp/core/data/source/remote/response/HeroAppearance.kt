package com.onedev.dicoding.superheroapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class HeroAppearance(
    @SerializedName("eye-color")
    val eyeColor: String? = null,
    val gender: String? = null,
    @SerializedName("hair-color")
    val hairColor: String? = null,
    val height: List<String>,
    val race: String? = null,
    val weight: List<String>
)