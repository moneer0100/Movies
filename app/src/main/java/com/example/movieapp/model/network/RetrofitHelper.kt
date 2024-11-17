package com.example.movieapp.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val url="https://api.themoviedb.org/3/"
    val retrofitinstanceCurrent= Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service= retrofitinstanceCurrent.create(ApiService::class.java)
}