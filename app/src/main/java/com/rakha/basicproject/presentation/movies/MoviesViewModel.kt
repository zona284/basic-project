package com.rakha.basicproject.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rakha.basicproject.core.domain.repository.MoviesRepository

/**
 *   Created By rakha
 *   28/10/22
 */
class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getAllMovies() = moviesRepository.getAllMovies().cachedIn(viewModelScope).asLiveData()

}