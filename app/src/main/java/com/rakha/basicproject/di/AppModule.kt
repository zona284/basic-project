package com.rakha.basicproject.di

import com.rakha.basicproject.presentation.favoriteMovies.FavoriteMoviesViewModel
import com.rakha.basicproject.presentation.movies.MoviesViewModel
import com.rakha.basicproject.presentation.moviesDetail.MoviesDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *   Created By rakha
 *   28/10/22
 */

val viewModelModule = module{
    viewModel { MoviesViewModel(get()) }
    viewModel { FavoriteMoviesViewModel(get()) }
    viewModel { MoviesDetailViewModel(get()) }
}