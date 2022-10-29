package com.rakha.basicproject.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rakha.basicproject.core.data.source.local.entity.MoviesEntity
import com.rakha.basicproject.core.data.source.local.room.FavoriteMoviesDao
import com.rakha.basicproject.core.data.source.local.room.MainDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 *   Created By rakha
 *   29/10/22
 */
@RunWith(AndroidJUnit4::class)
class FavoriteMoviesDaoTest {
    private lateinit var favorieMoviesDao: FavoriteMoviesDao
    private lateinit var db: MainDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MainDatabase::class.java).build()
        favorieMoviesDao = db.favoriteMoviesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadAllFavoriteMovies() = runBlocking {
        val data = MoviesEntity(
            movieId = 1,
            overview = "lorem ipsum dos silir amet",
            originalTitle = "original",
            title = "title",
            posterPath = "/img.jpg",
            backdropPath = "img.jpg",
            voteAverage = 2f
        )
        favorieMoviesDao.insertMovie(data)
        val detail = favorieMoviesDao.getMoviesList().first()
        assertThat(detail?.size, equalTo(1))
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadFavoriteMovies() = runBlocking {
        val data = MoviesEntity(
            movieId = 2,
            overview = "lorem ipsum dos silir amet",
            originalTitle = "original",
            title = "title",
            posterPath = "/img.jpg",
            backdropPath = "img.jpg",
            voteAverage = 2f
        )
        favorieMoviesDao.insertMovie(data)
        val detail = favorieMoviesDao.getMoviesById(2).first()
        assertThat(detail?.movieId, equalTo(data.movieId))
    }

    @Test
    @Throws(Exception::class)
    fun writeAndDeleteFavoriteMovies() = runBlocking {
        val data = MoviesEntity(
            movieId = 3,
            overview = "lorem ipsum dos silir amet",
            originalTitle = "original",
            title = "title",
            posterPath = "/img.jpg",
            backdropPath = "img.jpg",
            voteAverage = 2f
        )
        favorieMoviesDao.insertMovie(data)
        favorieMoviesDao.deleteById(3)
        val detail = favorieMoviesDao.getMoviesById(1).first()
        assertNull(detail)
    }
}