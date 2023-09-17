package com.example.ferrari.Retrofit

import com.example.ferrari.Utils.AppConstants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  Singleton design pattern. This ensures that you have a single instance of Retrofit that
 *  can be reused across different parts of your application
 *
 * We create a Kotlin object named MyRetrofitBuilder, which ensures that you have a single
 * instance of Retrofit and apiService throughout your app.
 *
 * The retrofit property is initialized using the lazy delegate to create Retrofit only
 * when it's first accessed. It's configured with your base URL and GsonConverterFactory.
 * its same as checking if its null or not and then returing it when creating an instance
 */
object MyRetrofitBuilder {
    private val retrofitBuilder : Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())

    }

    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }
}

/*
* private val retrofitBuilder : Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl("https://google-translate1.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())

    }


    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }*/