package com.example.movieapp.model.db


import com.example.movieapp.model.pojo.Result
import kotlinx.coroutines.flow.Flow

interface LocalDataMovie {
    fun getAllFavourite():Flow<List<Result>>
    suspend fun insertMovie(trendingPojo: Result)
    suspend fun deleteMovie(trendingPojo: Result)
}