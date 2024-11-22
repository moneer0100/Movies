package com.example.movieapp.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.ResponsState

import com.example.movieapp.model.pojo.RepoMovieImp
import com.example.movieapp.model.pojo.Result

import com.example.movieapp.model.pojo.TrendingPojo


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repoMovieImp: RepoMovieImp) : ViewModel() {
    private val _trendiding=MutableStateFlow<ResponsState<TrendingPojo>>(ResponsState.Loading)
    val trending=_trendiding.asStateFlow()
        fun getTrendingMovie(){
            viewModelScope.launch(Dispatchers.IO){
repoMovieImp.getTrending()?.catch {
    error->_trendiding.value=ResponsState.Error(error)
    Log.d("Tag", "getTrendingMovie:$error ")
}
    ?.collect{data->_trendiding.value=ResponsState.Success(data)
        Log.d("Tag", "getTrendingMovie:$data ")}
            }

        }
    private val _topRated=MutableStateFlow<ResponsState<TrendingPojo>>(ResponsState.Loading)
    val topRated=_topRated.asStateFlow()
    fun getTopRate(){
        viewModelScope.launch(Dispatchers.IO){
            repoMovieImp.getTopRated()?.catch {error->_topRated.value=ResponsState.Error(error)  }
                ?.collect{data->_topRated.value=ResponsState.Success(data)}
        }
    }
    private val _popularMovies=MutableStateFlow<ResponsState<TrendingPojo>>(ResponsState.Loading)
    val popularMovies=_popularMovies.asStateFlow()
    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO){
            repoMovieImp.getPopularMovies()?.catch { error->_popularMovies.value=ResponsState.Error(error) }
                ?.collect{data->_popularMovies.value=ResponsState.Success(data)
                    Log.d("Tag", "getPopularMovies:$data ")}

        }
    }
    private val _dicoverMovie=MutableStateFlow<ResponsState<TrendingPojo>>(ResponsState.Loading)
    val dicoverMovie=_dicoverMovie.asStateFlow()
    fun getDiscoveMovie(){
        viewModelScope.launch(Dispatchers.IO){
            repoMovieImp.getDiscoverMovies()?.catch { error-> _dicoverMovie.value=ResponsState.Error(error) }
                ?.collect{data->_dicoverMovie.value=ResponsState.Success(data)}
        }
    }
fun addMovieDatabase(result: Result){
viewModelScope.launch(Dispatchers.IO){
    repoMovieImp.insertMovie(result)
    Log.d("add", "addMovieDatabase:${result.posterPath} ")
}
}



}