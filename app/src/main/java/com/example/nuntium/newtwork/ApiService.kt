package com.example.nuntium.newtwork

import com.example.nuntium.models.News
import com.example.nuntium.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    fun getAllNews(
        @Query("q") query: String?,
        @Query("apiKey") apiKey: String = Constants.API_KEY,
    ): Call<News>

}