package com.onedev.dicoding.superheroapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class HeroBiography(
    val alignment: String? = null,
    @SerializedName("alter-egos")
    val alterEgos: String? = null,
    val aliases: List<String>,
    @SerializedName("first-appearance")
    val firstAppearance: String? = null,
    @SerializedName("full-name")
    val fullName: String? = null,
    @SerializedName("place-of-birth")
    val placeOfBirth: String? = null,
    val publisher: String? = null
)