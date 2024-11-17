package com.example.movieapp.model.pojo

import com.example.movieapp.model.network.MovieRemoteInterface

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RepoMovieImp(private val movieRemoteInterface: MovieRemoteInterface):RepoMovieInterface {
    companion object {
        private var instance: RepoMovieImp? = null
        fun getInstance(movieRemoteInterface: MovieRemoteInterface): RepoMovieImp {
            return instance ?: synchronized(this) {
                instance?: RepoMovieImp(movieRemoteInterface)
                    .also { instance = it }
            }
        }}

    override suspend fun getTrending(): Flow<TrendingPojo>  {
     return flowOf(movieRemoteInterface.getTrending())
    }

    override suspend fun getTopRated(): Flow<TrendingPojo> {
      return flowOf(movieRemoteInterface.getTopRated())
    }
}
