package com.example.movieapp.model.network

import com.example.movieapp.model.pojo.TrendingPojo


interface MovieRemoteInterface {
suspend fun getTrending():TrendingPojo
suspend fun getTopRated():TrendingPojo
}