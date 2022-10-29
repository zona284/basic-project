package com.rakha.basicproject.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *   Created By rakha
 *   26/10/22
 */
@Parcelize
data class Movies (
    val overview: String? = null,

    val originalLanguage: String? = null,

    val originalTitle: String? = null,

    val title: String? = null,

    val posterPath: String? = null,

    val backdropPath: String? = null,

    val releaseDate: String? = null,

    val voteAverage: Float? = null,

    val id: Int? = null
): Parcelable