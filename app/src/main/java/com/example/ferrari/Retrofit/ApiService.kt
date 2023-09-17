package com.example.ferrari.Retrofit

import com.example.ferrari.Model.data.Search.SongSearch
import com.example.ferrari.Utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(
        "X-RapidAPI-Key:${AppConstants.API_KEY}",
        "X-RapidAPI-Host:${AppConstants.HOST}"
    )
    @GET("search")
    suspend fun search(@Query("term") term: String,
                       @Query("locale") locale: String,
                       @Query("offset") offset: Int,
                       @Query("limit") limit: Int):    SongSearch

    /*@Headers(
            "content-type: application/x-www-form-urlencoded",
            "Accept-Encoding: application/gzip",
            "X-RapidAPI-Key: c106642465mshc41bbbbcba77000p1a180ajsn61ecd1924dbc",
            "X-RapidAPI-Host: google-translate1.p.rapidapi.com"
        )
        @POST("language/translate/v2")
        fun translateText(@Body requestBody: RequestBody): Call<TResponseModel>*/
}