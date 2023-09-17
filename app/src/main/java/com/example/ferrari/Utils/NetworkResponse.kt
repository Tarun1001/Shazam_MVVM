package com.example.ferrari.Utils

/**
 * Usually, we will get Loading, Success, and Failure type from API response.
 * Therefore, we will make Utility Class named Response.kt.
 * The code, much or less, will look like this:
 *
 * sealed class Response<out T> {
 *     object Loading: Response<Nothing>()
 *
 *     data class Success<out T>(
 *         val data: T?
 *     ): Response<T>()
 *
 *     data class Failure(
 *         val e: Exception?
 *     ): Response<Nothing>()
 * }
 *
 * In Kotlin, the `out` modifier is used in the context of generic types to denote
 * a covariant type parameter. When you declare a generic class or interface with `out`,
 * it means that the type parameter can only appear in "out" positions, which essentially means
 * it can be used as a return type but not as a parameter type in function signatures.
 * This is also known as declaration-site variance.
 *
 * ```kotlin
 * fun fetchNetworkResponse(): NetworkResponse<Derived> {
 *     // ...
 * }
 * ```
 * But you cannot use `T` as a parameter type in functions of `NetworkResponse`:
 *
 * ```kotlin
 * fun processNetworkResponse(data: T) { // This is not allowed
 *     // ...
 * }
 * ```
 * This restriction ensures type safety when working with covariant types. It allows you to safely use subclasses of `T` without breaking the expected behavior of the generic class or interface.
 *
 * In the context of `NetworkResponse`, this covariant type parameter might be used to represent various response types (e.g., success or error responses) where the actual response data type (`T`) can vary depending on the specific type of network response.
 */

//Each time we call something from API, we will refer to this class to find out which response type API response will give us.

sealed class NetworkResponse<out T> {

    object Loading: NetworkResponse<Nothing>()

    data class Success<out T>(val data:T?):  NetworkResponse<T>()

    data class Failure(val exception: Exception): NetworkResponse<Nothing>()

}

