package com.example.movieapp.model.pojo
import kotlinx.coroutines.flow.Flow

interface RepoMovieInterface {
    suspend fun getTrending(): Flow<TrendingPojo>
    suspend fun getTopRated():Flow<TrendingPojo>
    suspend fun getPopularMovies():Flow<TrendingPojo>
    suspend fun getDiscoverMovies():Flow<TrendingPojo>
    suspend fun getSearch(search:String):Flow<TrendingPojo>
    // fav
fun getAllFavourit():Flow<List<Result>>
suspend fun insertMovie(trendingPojo: Result)
suspend fun deleteMovie(trendingPojo: Result)

}