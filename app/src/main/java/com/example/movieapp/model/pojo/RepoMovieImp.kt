package com.example.movieapp.model.pojo

import com.example.movieapp.model.db.LocalDataMovie
import com.example.movieapp.model.db.LocalDataMovieImp
import com.example.movieapp.model.network.MovieRemoteInterface

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RepoMovieImp(private val movieRemoteInterface: MovieRemoteInterface,
    private val localDataMovie: LocalDataMovie):RepoMovieInterface {
    companion object {
        private var instance: RepoMovieImp? = null
        fun getInstance(movieRemoteInterface: MovieRemoteInterface, localDataMovie: LocalDataMovieImp): RepoMovieImp {
            return this.instance ?: synchronized(this) {
                this.instance ?: RepoMovieImp(movieRemoteInterface,localDataMovie)
                    .also { this.instance = it }
            }
        }}

    override suspend fun getTrending(): Flow<TrendingPojo>  {
     return flowOf(movieRemoteInterface.getTrending())
    }

    override suspend fun getTopRated(): Flow<TrendingPojo> {
      return flowOf(movieRemoteInterface.getTopRated())
    }

    override suspend fun getPopularMovies(): Flow<TrendingPojo> {
       return flowOf(movieRemoteInterface.getpopularMovies())
    }

    override suspend fun getDiscoverMovies(): Flow<TrendingPojo> {
       return flowOf(movieRemoteInterface.getDiscoverMovie())
    }

    override suspend fun getSearch(search: String): Flow<TrendingPojo> {
       return flowOf(movieRemoteInterface.getSearch(search))
    }

    override fun getAllFavourit(): Flow<List<Result>> {
        return localDataMovie.getAllFavourite()
    }

    override suspend fun insertMovie(trendingPojo: Result) {
     return localDataMovie.insertMovie(trendingPojo)
    }

    override suspend fun deleteMovie(trendingPojo: Result) {
     localDataMovie.deleteMovie(trendingPojo)
    }
}
