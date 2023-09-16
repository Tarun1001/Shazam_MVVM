package com.example.ferrari

import com.example.ferrari.Model.data.Search.SongSearch

class ShazamRepository(private val apiService: ApiService ) {

    suspend fun SearchTrack(term: String,
                            locale: String,
                            offset: Int,
                            limit: Int): NetworkResponse<SongSearch>
    {
        return try {
            val responseData= apiService.search(term, locale, offset, limit)
            return NetworkResponse.Success(responseData)

        } catch (e: Exception) {
            NetworkResponse.Failure(e)
        }


    }
}
/**
 * 1. Repository with Coroutines:
 *
 * Network Operations: Repositories often handle network operations,
 * such as making API requests using Retrofit. Using coroutines in the
 * repository allows you to perform these potentially long-running operations
 * asynchronously without blocking the main (UI) thread.
 *
 * Data Transformation: Repositories might also perform data transformation
 * or mapping, converting raw API responses into domain models or database entities.
 * Coroutines can be helpful for these tasks, especially when they involve multiple
 * asynchronous operations.
 *
 * Error Handling: Coroutines in the repository can be used for error handling,
 * such as catching exceptions that occur during network requests and converting
 * them into a consistent error format (e.g., NetworkResponse.Error as shown in your
 * previous code examples).
 */