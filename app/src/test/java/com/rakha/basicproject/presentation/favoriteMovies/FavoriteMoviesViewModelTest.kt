package com.rakha.basicproject.presentation.favoriteMovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rakha.core.data.Resource
import com.rakha.core.domain.model.Movies
import com.rakha.core.domain.repository.MoviesRepository
import com.rakha.basicproject.helper.TestCoroutineRule
import com.rakha.basicproject.helper.getOrAwaitValue
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


/**
 * Created By rakha
 * 29/10/22
 */
@ExperimentalCoroutinesApi
class FavoriteMoviesViewModelTest {
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule(coroutineDispatcher)

    @Mock
    private lateinit var repository: MoviesRepository

    private lateinit var favoriteMoviesViewModel: FavoriteMoviesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        favoriteMoviesViewModel = FavoriteMoviesViewModel(repository)
    }

    @Test
    fun `testGetAllMovies`() {
        val expectedResult = flow {
            emit(
                Resource.Success(
                    listOf(
                        Movies(id = 1, title = "test 1"),
                        Movies(id = 2, title = "test 2")
                    )
                )
            )
        }
        Mockito.`when`(repository.getFavoriteMovies()).thenReturn(expectedResult)

        testCoroutineRule.runBlockingTest {
            val result = favoriteMoviesViewModel.getFavMovies().getOrAwaitValue()
            val expected = expectedResult.first()

            assertNotNull(result.data)
            assertEquals(result.data?.size, expected.data?.size)
        }

    }
}