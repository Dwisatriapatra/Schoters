package com.example.schoters.networking

import com.example.schoters.model.GetAllNewsByKeywordResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    // endpoint: everything
    @GET("everything")
    fun getNewsByKeyword(
        @Query("q") keyword: String,
        @Query("apiKey") apiKey: String
    ) : Call<GetAllNewsByKeywordResponse>
}