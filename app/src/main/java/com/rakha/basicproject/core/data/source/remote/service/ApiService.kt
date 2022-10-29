package com.rakha.basicproject.core.data.source.remote.service

import com.rakha.basicproject.core.data.source.remote.response.BaseResponse
import com.rakha.basicproject.core.data.source.remote.response.MoviesResponse
import retrofit2.http.*

/**
 *   Created By rakha
 *   26/10/22
 */
interface ApiService {

    @GET("movie/popular")
    suspend fun getMoviesList(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): BaseResponse<List<MoviesResponse>>

}