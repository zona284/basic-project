package com.rakha.core.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rakha.core.data.source.remote.paging.MoviesPagingDataSource
import com.rakha.core.data.source.remote.service.ApiService

/**
 *   Created By rakha
 *   26/10/22
 */
class RemoteDataSource (private val apiService: ApiService) {

    fun getAllMovies() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { MoviesPagingDataSource(apiService) },
    ).flow

}