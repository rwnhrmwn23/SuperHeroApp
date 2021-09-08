package com.onedev.dicoding.superheroapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class HeroConnections(
    @SerializedName("group-affiliation")
    val groupAffiliation: String? = null,
    val relatives: String? = null
)