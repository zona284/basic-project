package com.rakha.basicproject.presentation.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.rakha.basicproject.helper.TestCoroutineRule
import com.rakha.basicproject.helper.collectData
import com.rakha.basicproject.core.domain.model.Movies
import com.rakha.basicproject.core.domain.repository.MoviesRepository
import com.rakha.basicproject.helper.getOrAwaitValue
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created By rakha
 * 29/10/22
 */
@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    private val coroutineDispatcher = TestCoroutineDispatcher()
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule(coroutineDispatcher)

    @Mock
    private lateinit var repository: MoviesRepository

    private lateinit var moviesViewModel: MoviesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        moviesViewModel = MoviesViewModel(repository)
    }

    @Test
    fun `testGetAllMovies`() {
        val expectedResult = flow {
            emit(
                PagingData.from(
                    listOf(
                        Movies(id = 1, title = "test 1"),
                        Movies(id = 2, title = "test 2")
                    )
                )
            )
        }
        Mockito.`when`(repository.getAllMovies()).thenReturn(expectedResult)

        testCoroutineRule.runBlockingTest {
            val result = moviesViewModel.getAllMovies().getOrAwaitValue()
            val expected = expectedResult.first().collectData(coroutineDispatcher)
            val actual = result.collectData(coroutineDispatcher)

            assertNotNull(actual)
            assertEquals(expected[0].id, actual[0].id)
        }

    }
}