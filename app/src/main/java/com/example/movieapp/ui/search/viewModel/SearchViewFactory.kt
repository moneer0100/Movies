package com.example.movieapp.ui.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.model.pojo.RepoMovieImp

class SearchViewFactory(private val repo:RepoMovieImp):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            SearchViewModel(repo) as T
        } else {
            throw java.lang.IllegalArgumentException("ViewModel Class not found")
        }
    }

}