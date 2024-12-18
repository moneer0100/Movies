package com.example.movieapp.model.network

import com.example.movieapp.model.pojo.TrendingPojo


class MovieRemoteImp private constructor(api:ApiService):MovieRemoteInterface{
    val apiService:ApiService by lazy {  RetrofitHelper.retrofitinstanceCurrent.create(ApiService::class.java)
    }
    companion object {
        private var instance: MovieRemoteImp? = null
        fun getInstance(apiService: ApiService): MovieRemoteImp {
            return instance?: synchronized(this){
                instance ?: MovieRemoteImp(apiService)
                    .also { instance = it }
            }
        }}
    override suspend fun getTrending(): TrendingPojo {
     return apiService.getTrending()
    }

    override suspend fun getTopRated(): TrendingPojo {
      return apiService.getTopRated()
    }

    override suspend fun getpopularMovies(): TrendingPojo {
        return apiService.getPopularMovies()
    }

    override suspend fun getDiscoverMovie(): TrendingPojo {
      return apiService.getDiscoverMovies()
    }

    override suspend fun getSearch(search: String): TrendingPojo {
        return apiService.getSearch(search = search)
    }


}