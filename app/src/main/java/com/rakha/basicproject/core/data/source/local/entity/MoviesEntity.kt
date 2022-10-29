package com.rakha.basicproject.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *   Created By rakha
 *   28/10/22
 */
@Entity(tableName = "fav_movies")
data class MoviesEntity (

    @PrimaryKey(autoGenerate = true)
    val _id: Int? = null,

    val movieId: Int? = null,

    val overview: String? = null,

    val originalLanguage: String? = null,

    val originalTitle: String? = null,

    val title: String? = null,

    val posterPath: String? = null,

    val backdropPath: String? = null,

    val releaseDate: String? = null,

    val voteAverage: Float? = null
)