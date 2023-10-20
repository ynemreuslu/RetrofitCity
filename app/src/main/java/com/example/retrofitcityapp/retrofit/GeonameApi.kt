package com.example.retrofitcityapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeonameApi {
    @GET("/wikipediaSearchJSON")
    fun findWikipedia(
        @Query("username") username: String,
        @Query("q") city: String,
        @Query("formatted") format: Boolean
    ): Call<Root>
}