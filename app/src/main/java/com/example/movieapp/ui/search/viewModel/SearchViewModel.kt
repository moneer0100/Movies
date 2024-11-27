package com.example.movieapp.ui.search.viewModel

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

class SearchViewModel(private  val repo:RepoMovieImp) : ViewModel() {
private val _search= MutableStateFlow<ResponsState<TrendingPojo>>(ResponsState.Loading)
    val search =_search.asStateFlow()

    fun getSearch(search:String){
        viewModelScope.launch(Dispatchers.IO){
            repo.getSearch(search)?.catch {error->_search.value=ResponsState.Error(error)  }
                ?.collect{data->_search.value=ResponsState.Success(data)
                    Log.d("search", "getSearch$data: ")}
        }
    }

}