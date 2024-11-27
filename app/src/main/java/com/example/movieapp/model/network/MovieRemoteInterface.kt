package com.example.movieapp.model.network

import com.example.movieapp.model.pojo.TrendingPojo



interface MovieRemoteInterface {
suspend fun getTrending(): TrendingPojo
suspend fun getTopRated():TrendingPojo
suspend fun getpopularMovies():TrendingPojo
suspend fun getDiscoverMovie():TrendingPojo
suspend fun getSearch(search :String):TrendingPojo
}