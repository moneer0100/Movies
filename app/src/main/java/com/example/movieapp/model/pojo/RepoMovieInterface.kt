package com.example.movieapp.model.pojo


import kotlinx.coroutines.flow.Flow

interface RepoMovieInterface {
    suspend fun getTrending(): Flow<TrendingPojo>
    suspend fun getTopRated():Flow<TrendingPojo>

}