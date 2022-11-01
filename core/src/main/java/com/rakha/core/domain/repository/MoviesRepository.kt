package com.rakha.core.domain.repository

import androidx.paging.PagingData
import com.rakha.core.data.Resource
import com.rakha.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

/**
 *   Created By rakha
 *   28/10/22
 */
interface MoviesRepository {
    fun getAllMovies(): Flow<PagingData<Movies>>

    fun getFavoriteMovies(): Flow<Resource<List<Movies>>>

    fun checkIsFavorite(movieId: Int): Flow<Boolean>

    suspend fun addToFavorite(movies: Movies)

    suspend fun deleteFavorite(movieId: Int)

}