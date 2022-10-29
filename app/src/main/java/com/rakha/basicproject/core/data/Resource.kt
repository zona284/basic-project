package com.rakha.basicproject.core.data

/**
 *   Created By rakha
 *   25/10/22
 *   Resource Event State
 *   Data fetched from datasource map into Event State that will be consumed by view
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
}