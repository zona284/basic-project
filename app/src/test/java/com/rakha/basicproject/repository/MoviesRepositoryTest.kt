package com.rakha.basicproject.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rakha.basicproject.core.data.source.local.LocalDataSource
import com.rakha.basicproject.core.data.source.local.entity.MoviesEntity
import com.rakha.basicproject.core.data.source.remote.RemoteDataSource
import com.rakha.basicproject.core.datamapper.MoviesDataMapper
import com.rakha.basicproject.core.domain.model.Movies
import com.rakha.basicproject.core.domain.repository.MoviesRepository
import com.rakha.basicproject.core.domain.repository.MoviesRepositoryImpl
import com.rakha.basicproject.helper.TestCoroutineRule
import junit.framework.TestCase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 *   Created By rakha
 *   29/10/22
 */
@ExperimentalCoroutinesApi
class MoviesRepositoryTest {
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule(coroutineDispatcher)

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var localDataSource: LocalDataSource

    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        moviesRepository = MoviesRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun getAllMovies() {
        moviesRepository.getAllMovies()
        verify(remoteDataSource, times(1)).getAllMovies()
    }

    @Test
    fun getFavoriteMovies() {
        val expectedResult = flow {
            emit(listOf(
                MoviesEntity(movieId = 1, title = "test"),
                MoviesEntity(movieId = 2, title = "test 2")
            ))
        }
        `when`(localDataSource.getFavMovies()).thenReturn(expectedResult)
        testCoroutineRule.runBlockingTest {
            val result = moviesRepository.getFavoriteMovies().last()
            val expected = expectedResult.first()
            assertEquals(expected.size, result.data?.size)
        }
    }

    @Test
    fun checkIsFavorite() {
        val expectedResult = flow {
            emit(MoviesEntity(movieId = 1, title = "test"))
        }
        val movieId = 1
        `when`(localDataSource.getFavMoviesById(movieId)).thenReturn(expectedResult)
        testCoroutineRule.runBlockingTest {
            val result = moviesRepository.checkIsFavorite(movieId).first()
            val expected = expectedResult.first()
            assertNotNull(expected)
            assertTrue(result)
        }
    }

    @Test
    fun addToFavorite() {
        val movies = Movies(
            id = 1,
            title = "test"
        )
        testCoroutineRule.runBlockingTest {
            moviesRepository.addToFavorite(movies)
            verify(localDataSource, times(1)).addFavMovie(MoviesDataMapper.modelToEntity(movies))
        }
    }

    @Test
    fun deleteFavorite() {
        val movieId = 1
        testCoroutineRule.runBlockingTest {
            moviesRepository.deleteFavorite(movieId)
            verify(localDataSource, times(1)).deleteFavMovieById(movieId)
        }
    }
}