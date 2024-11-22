package com.example.movieapp.ui.fav.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.network.ResponsState
import com.example.movieapp.model.pojo.RepoMovieImp
import com.example.movieapp.model.pojo.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repo:RepoMovieImp) : ViewModel() {
private val _favMovie= MutableStateFlow<ResponsState<List<Result>>>(ResponsState.Loading)
    val favMovie=_favMovie.asStateFlow()
    fun getFavData(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getAllFavourit()?.catch {
                error->_favMovie.value=ResponsState.Error(error)
            }?.collect{data->_favMovie.value=ResponsState.Success(data)
                Log.d("Fav", "getFavData:$data ")}

        }

    }
fun deletMovie(result:Result){
    viewModelScope.launch(Dispatchers.IO){
        repo.deleteMovie(result)
    }
}
}