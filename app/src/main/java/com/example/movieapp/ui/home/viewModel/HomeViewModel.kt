package com.example.movieapp.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.ResponsState
import com.example.movieapp.model.pojo.RepoMovieImp
import com.example.movieapp.model.pojo.TrendingPojo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlin.math.log

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



}