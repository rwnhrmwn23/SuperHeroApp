package com.onedev.dicoding.superheroapp.ui

import com.onedev.dicoding.superheroapp.core.domain.model.Hero

interface ItemClicked {
    fun itemClicked(hero: Hero)
}