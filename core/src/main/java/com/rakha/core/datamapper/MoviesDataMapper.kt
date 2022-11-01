package com.rakha.core.datamapper

import com.rakha.core.data.source.local.entity.MoviesEntity
import com.rakha.core.data.source.remote.response.MoviesResponse
import com.rakha.core.domain.model.Movies

/**
 *   Created By rakha
 *   28/10/22
 *   Object Data Mapper between Model, Response and Entity
 */

object MoviesDataMapper {
    fun modelToEntity(movies: Movies?): MoviesEntity {
        return MoviesEntity(
            movieId = movies?.id,
            overview = movies?.overview,
            originalLanguage = movies?.originalLanguage,
            originalTitle = movies?.originalTitle,
            title = movies?.title,
            posterPath = movies?.posterPath,
            backdropPath = movies?.backdropPath,
            releaseDate = movies?.releaseDate,
            voteAverage = movies?.voteAverage
        )
    }

    fun responseToModel(moviesResponse: MoviesResponse?): Movies {
        return Movies(
            id = moviesResponse?.id,
            overview = moviesResponse?.overview,
            originalLanguage = moviesResponse?.originalLanguage,
            originalTitle = moviesResponse?.originalTitle,
            title = moviesResponse?.title,
            posterPath = moviesResponse?.posterPath,
            backdropPath = moviesResponse?.backdropPath,
            releaseDate = moviesResponse?.releaseDate,
            voteAverage = moviesResponse?.voteAverage
        )
    }

    fun entityToModel(moviesEntity: MoviesEntity?): Movies {
        return Movies(
            id = moviesEntity?.movieId,
            overview = moviesEntity?.overview,
            originalLanguage = moviesEntity?.originalLanguage,
            originalTitle = moviesEntity?.originalTitle,
            title = moviesEntity?.title,
            posterPath = moviesEntity?.posterPath,
            backdropPath = moviesEntity?.backdropPath,
            releaseDate = moviesEntity?.releaseDate,
            voteAverage = moviesEntity?.voteAverage
        )
    }
}