package com.rakha.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: T? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)
