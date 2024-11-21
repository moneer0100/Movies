package com.example.movieapp.model.pojo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TrendingPojo(
    val page: Long,
    val results: List<Result>,
    val totalPages: Long,
    val totalResults: Long
)
@Entity(tableName = "favorite")
data class Result(
    @PrimaryKey val id: Long, // تأكد من تحديد المفتاح الرئيسي
    val backdropPath: String? = null,
    var title: String? = null, // يمكن أن يكون null
    val originalTitle: String? = null, // يمكن أن يكون null
    var overview: String? = null, // يمكن أن يكون null
    @SerializedName("poster_path")
    val posterPath: String? = null, // يمكن أن يكون null
    val mediaType: MediaType? = null,
    val adult: Boolean,
    val originalLanguage: OriginalLanguage? = null,
    val genreIDS: List<Long>? = null,
    val popularity: Double = 0.0,
    val releaseDate: String? = null,
    val video: Boolean,
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Long = 0
):Serializable


enum class MediaType  {
    Movie
}
enum class OriginalLanguage {
    En,
    Fr
}
