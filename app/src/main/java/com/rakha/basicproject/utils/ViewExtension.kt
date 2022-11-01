package com.rakha.basicproject.utils

import android.content.Context
import android.widget.ImageView
import com.rakha.core.BuildConfig

/**
 *   Created By rakha
 *   28/10/22
 */

fun ImageView.loadImageFromUrl(url: String) {
    GlideApp
        .with(this)
        .load(url)
        .into(this)
}

fun generateImageUrl(image: String): String {
    return BuildConfig.BASE_IMAGE_URL+image
}