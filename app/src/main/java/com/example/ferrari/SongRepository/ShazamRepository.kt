package com.example.ferrari.SongRepository

import android.util.Log
import com.example.ferrari.Model.data.Search.SongSearch
import com.example.ferrari.Utils.NetworkResponse
import com.example.ferrari.Retrofit.ApiService
import java.io.IOException
import java.net.UnknownHostException

class ShazamRepository(private val apiService: ApiService) {

    suspend fun SearchTrack(
        term: String,
        locale: String,
        offset: Int,
        limit: Int
    ): NetworkResponse<SongSearch> {
        return try {
            val response = apiService.search(term, locale, offset, limit)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                   // Log.d("Flow", responseBody.toString())
                    NetworkResponse.Success(responseBody)
                } else {
                    NetworkResponse.Failure(
                        response.code(),
                        "Response body is empty",
                        null
                    )
                }
            } else {
                NetworkResponse.Failure(
                    response.code(),
                    response.message(),
                    null
                )
            }
        } catch (e: IOException) {
            NetworkResponse.Failure(
                null,
                "An error occurred while communicating with the server. Please check your internet connection.",
                e
            )
        } catch (e: UnknownHostException) {
            NetworkResponse.Failure(
                null,
                "Unable to connect to the server. Please check your internet connection.",
                e
            )
        } catch (e: Exception) {
            NetworkResponse.Failure(
                null,
                "An unexpected error occurred.",
                e
            )
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