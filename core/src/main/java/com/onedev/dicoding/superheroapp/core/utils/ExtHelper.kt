package com.onedev.dicoding.superheroapp.core.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.onedev.dicoding.superheroapp.core.BuildConfig
import com.onedev.dicoding.superheroapp.core.R

object ExtHelper {
    const val DATABASE_NAME = BuildConfig.DATABASE_NAME
    const val DATABASE_PASSWORD = BuildConfig.DATABASE_PASSWORD
    const val HOSTNAME = BuildConfig.HOSTNAME
    const val ACCESS_TOKEN = BuildConfig.ACCESS_TOKEN

    fun ImageView.loadImage(url: String) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_image_loading)
            .error(R.drawable.ic_image_error)
            .into(this)
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }

    fun String.convertToInt(): Int = if (this == "" || this == "null") 0 else this.toInt()

    fun String.convertNullValue(): String = if (this == "" || this == "null") "-" else this
}