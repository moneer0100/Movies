package com.example.movieapp.model.db

import com.example.movieapp.model.pojo.Result
import kotlinx.coroutines.flow.Flow

class LocalDataMovieImp(private val dao: Dao):LocalDataMovie {

    companion object {
        @Volatile
        var instance: LocalDataMovieImp? = null
        fun getInstance(dao: Dao): LocalDataMovieImp {
            return instance?: synchronized(this){
                instance?: LocalDataMovieImp(dao)
                    .also { instance = it }
            }
        }
    }

    override fun getAllFavourite(): Flow<List<Result>> {
       return dao.getAllFavourite()
    }

    override suspend fun insertMovie(trendingPojo: Result) {
      return dao.insertFavourite(trendingPojo)
    }

    override suspend fun deleteMovie(trendingPojo: Result) {
        return dao.deletFavourite(trendingPojo)
    }
}