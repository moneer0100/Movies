package com.example.movieapp.model.network

sealed class ResponsState<out T> {
    data class Success<out T>(val data: T) : ResponsState<T>()
    data class Error(val message: Throwable) : ResponsState<Nothing>()
    object Loading : ResponsState<Nothing>()
}