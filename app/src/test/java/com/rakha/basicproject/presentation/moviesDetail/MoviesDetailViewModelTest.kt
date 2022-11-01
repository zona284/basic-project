package com.rakha.basicproject.presentation.moviesDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rakha.core.domain.model.Movies
import com.rakha.core.domain.repository.MoviesRepository
import com.rakha.basicproject.helper.TestCoroutineRule
import com.rakha.basicproject.helper.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created By rakha
 * 29/10/22
 */
@ExperimentalCoroutinesApi
class MoviesDetailViewModelTest {
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule(coroutineDispatcher)

    @Mock
    private lateinit var repository: MoviesRepository

    private lateinit var moviesDetailViewModel: MoviesDetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        moviesDetailViewModel = MoviesDetailViewModel(repository)
    }

    @Test
    fun checkFavorite() {
        val expectedResult = flow {
            emit(true)
        }
        Mockito.`when`(repository.checkIsFavorite(1)).thenReturn(expectedResult)

        testCoroutineRule.runBlockingTest {
            moviesDetailViewModel.checkFavorite(1)
            val result = moviesDetailViewModel.isFavorite.getOrAwaitValue()
            val expected = expectedResult.first()

            assertTrue(result)
            assertEquals(result, expected)
        }

    }

    @Test
    fun addFavorite() {
        val movies = Movies(id = 1, title = "test")
        testCoroutineRule.runBlockingTest {
            moviesDetailViewModel.addFavorite(movies)
            val result = moviesDetailViewModel.isFavorite.getOrAwaitValue()
            assertTrue(result)
        }
    }

    @Test
    fun deleteFavorite() {
        testCoroutineRule.runBlockingTest {
            moviesDetailViewModel.deleteFavorite(1)
            val result = moviesDetailViewModel.isFavorite.getOrAwaitValue()
            assertFalse(result)
        }
    }
}