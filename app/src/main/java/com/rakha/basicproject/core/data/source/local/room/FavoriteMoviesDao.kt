package com.rakha.basicproject.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rakha.basicproject.core.data.source.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

/**
 *   Created By rakha
 *   28/10/22
 */
@Dao
interface FavoriteMoviesDao {

    @Query("SELECT * FROM fav_movies")
    fun getMoviesList(): Flow<List<MoviesEntity>?>

    @Query("SELECT * FROM fav_movies WHERE movieId = :movieId LIMIT 1")
    fun getMoviesById(movieId: Int): Flow<MoviesEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MoviesEntity)

    @Query("DELETE FROM fav_movies WHERE movieId = :movieId")
    suspend fun deleteById(movieId: Int)
}