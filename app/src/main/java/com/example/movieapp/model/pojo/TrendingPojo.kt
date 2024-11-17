package com.example.movieapp.model.pojo


import com.google.gson.annotations.SerializedName

data class TrendingPojo(val page: Long,
                        val results: List<Result>,
                        val totalPages: Long,
                        val totalResults: Long)
data class Result (
    val backdropPath: String,
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val mediaType: MediaType,
    val adult: Boolean,
    val originalLanguage: OriginalLanguage,
    val genreIDS: List<Long>,
    val popularity: Double,
    val releaseDate: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)

enum class MediaType {
    Movie
}

enum class OriginalLanguage {
    En,
    Fr
}
