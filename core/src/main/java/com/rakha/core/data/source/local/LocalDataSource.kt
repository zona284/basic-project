package com.rakha.core.data.source.local

import com.rakha.core.data.source.local.entity.MoviesEntity
import com.rakha.core.data.source.local.room.FavoriteMoviesDao
import kotlinx.coroutines.flow.Flow

/**
 *   Created By rakha
 *   28/10/22
 */
class LocalDataSource(private val favoriteMoviesDao: FavoriteMoviesDao) {
    // Favorite
    fun getFavMovies(): Flow<List<MoviesEntity>?> = favoriteMoviesDao.getMoviesList()
    fun getFavMoviesById(movieId: Int): Flow<MoviesEntity?> = favoriteMoviesDao.getMoviesById(movieId)
    suspend fun addFavMovie(movies: MoviesEntity) = favoriteMoviesDao.insertMovie(movies)
    suspend fun deleteFavMovieById(movieId: Int) = favoriteMoviesDao.deleteById(movieId)
}