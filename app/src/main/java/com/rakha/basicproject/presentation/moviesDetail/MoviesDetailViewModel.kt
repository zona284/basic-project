package com.rakha.basicproject.presentation.moviesDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakha.basicproject.core.domain.model.Movies
import com.rakha.basicproject.core.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *   Created By rakha
 *   29/10/22
 */
class MoviesDetailViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    var isFavorite = MutableLiveData<Boolean>()

    fun checkFavorite(movieId: Int) {
        viewModelScope.launch {
            val response = moviesRepository.checkIsFavorite(movieId).first()
            withContext(Dispatchers.Main) {
                response.apply {
                    isFavorite.postValue(this)
                }
            }
        }
    }

    fun addFavorite(movies: Movies) {
        viewModelScope.launch {
            moviesRepository.addToFavorite(movies)
            withContext(Dispatchers.Main) {
                isFavorite.postValue(true)
            }
        }
    }

    fun deleteFavorite(movieId: Int) {
        viewModelScope.launch {
            moviesRepository.deleteFavorite(movieId)
            withContext(Dispatchers.Main) {
                isFavorite.postValue(false)
            }
        }
    }
}