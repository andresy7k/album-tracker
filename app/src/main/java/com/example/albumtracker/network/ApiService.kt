package com.example.albumtracker.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("searchplayers.php")
    suspend fun searchPlayer(@Query("p") playerName: String): Response<PlayerResponse>
}