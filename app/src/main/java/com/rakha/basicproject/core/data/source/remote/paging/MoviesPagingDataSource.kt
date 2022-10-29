package com.rakha.basicproject.core.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rakha.basicproject.BuildConfig
import com.rakha.basicproject.core.data.source.remote.service.ApiService
import com.rakha.basicproject.core.datamapper.MoviesDataMapper
import com.rakha.basicproject.core.domain.model.Movies

/**
 *   Created By rakha
 *   28/10/22
 */
class MoviesPagingDataSource(private val apiService: ApiService) : PagingSource<Int, Movies>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        return try {
            // Start refresh at page 1 if undefined.
            val position = params.key ?: 1
            val response = apiService.getMoviesList(BuildConfig.API_KEY, position)
            val data = response.results?.map { MoviesDataMapper.responseToModel(it) }

            return LoadResult.Page(
                data = data ?: listOf(),
                prevKey = null, // Only paging forward.,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}