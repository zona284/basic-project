package com.rakha.basicproject.core.domain.repository

import androidx.paging.PagingData
import com.rakha.basicproject.core.data.Resource
import com.rakha.basicproject.core.data.source.local.LocalDataSource
import com.rakha.basicproject.core.data.source.remote.RemoteDataSource
import com.rakha.basicproject.core.datamapper.MoviesDataMapper
import com.rakha.basicproject.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 *   Created By rakha
 *   28/10/22
 */
class MoviesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): MoviesRepository {
    override fun getAllMovies(): Flow<PagingData<Movies>> = remoteDataSource.getAllMovies()

    override fun getFavoriteMovies(): Flow<Resource<List<Movies>>> = flow {
        emit(Resource.Loading())
        val data = localDataSource.getFavMovies().first()
        emit(Resource.Success(data?.map { MoviesDataMapper.entityToModel(it) }))
    }

    override fun checkIsFavorite(movieId: Int): Flow<Boolean> = flow {
        emit(localDataSource.getFavMoviesById(movieId).first() != null)
    }

    override suspend fun addToFavorite(movies: Movies) {
        localDataSource.addFavMovie(MoviesDataMapper.modelToEntity(movies))
    }

    override suspend fun deleteFavorite(movieId: Int) {
        localDataSource.deleteFavMovieById(movieId)
    }
}