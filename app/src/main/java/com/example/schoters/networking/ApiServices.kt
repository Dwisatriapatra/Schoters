package com.example.schoters.networking

import com.example.schoters.model.GetAllNewsByKeywordResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiServices {

    // endpoint: everything
    @GET("everything")
    @FormUrlEncoded
    fun getNewsByKeyword(
        @Header("apiKey") apiKey: String,
        @Header("q") keyword: String
    ) : Call<GetAllNewsByKeywordResponse>

}