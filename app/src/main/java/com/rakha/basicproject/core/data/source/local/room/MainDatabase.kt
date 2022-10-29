package com.rakha.basicproject.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rakha.basicproject.core.data.source.local.entity.MoviesEntity

/**
 *   Created By rakha
 *   28/10/22
 */
@Database(
    entities = [
        MoviesEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase: RoomDatabase() {
    abstract fun favoriteMoviesDao(): FavoriteMoviesDao
}