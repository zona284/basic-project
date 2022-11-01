package com.rakha.basicproject.presentation.favoriteMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rakha.core.domain.repository.MoviesRepository

/**
 *   Created By rakha
 *   28/10/22
 */
class FavoriteMoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getFavMovies() = moviesRepository.getFavoriteMovies().asLiveData()
}