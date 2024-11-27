package com.example.movieapp.model.network

import com.example.movieapp.model.pojo.TrendingPojo

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
  @GET("trending/movie/day")
suspend fun getTrending(
@Query("api_key") api_key:String="98cab6930c64eb0b9e6ba2885bf7d48a"
):TrendingPojo
@GET("movie/top_rated")
suspend fun getTopRated(
  @Query("api_key") api_key:String="98cab6930c64eb0b9e6ba2885bf7d48a"
): TrendingPojo
@GET("movie/popular")
suspend fun getPopularMovies(
  @Query("api_key")  api_key:String="98cab6930c64eb0b9e6ba2885bf7d48a"
):TrendingPojo
  @GET("discover/movie")
  suspend fun getDiscoverMovies(
    @Query("api_key")  api_key:String="98cab6930c64eb0b9e6ba2885bf7d48a"
  ):TrendingPojo
  @GET("search/movie")
  suspend fun getSearch(@Query("api_key") api_key:String="98cab6930c64eb0b9e6ba2885bf7d48a"
                        ,@Query("query") search:String):TrendingPojo
}
