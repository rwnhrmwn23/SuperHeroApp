package com.onedev.dicoding.superheroapp.ui.fragment.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onedev.dicoding.superheroapp.core.data.SuperHeroRepository

class FavoriteViewModel(superHeroRepository: SuperHeroRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text
}