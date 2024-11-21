package com.example.movieapp.model.pojo

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Convertor {
    @TypeConverter

fun fromListToString(genreIds: List<Long>?): String {
    return Gson().toJson(genreIds)
}

    @TypeConverter
    fun fromStringToList(genreIdsString: String?): List<Long>? {
        return if (genreIdsString.isNullOrEmpty()) {
            emptyList()
        } else {
            val type = object : TypeToken<List<Long>>() {}.type
            Gson().fromJson(genreIdsString, type)
        }
    }
}