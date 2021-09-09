package com.onedev.dicoding.superheroapp.fragment

import com.onedev.dicoding.superheroapp.core.domain.model.Hero

interface ItemClicked {
    fun itemClicked(hero: Hero)
}