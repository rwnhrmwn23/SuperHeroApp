package com.onedev.dicoding.superheroapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    val response: String,
    @SerializedName("results-for")
    val resultsFor: String,
    val results: List<HeroResults>,
)