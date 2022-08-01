package com.example.amazon.interfaces

import com.example.amazon.models.ApiModel
import retrofit2.Call
import retrofit2.http.GET

interface ModelApi {
    @GET("1aa46e47-823f-4f58-bfc1-96a1aff910cc")
    fun getQuote(): Call<List<ApiModel>>
}