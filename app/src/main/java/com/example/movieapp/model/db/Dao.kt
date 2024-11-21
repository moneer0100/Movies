package com.example.movieapp.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.model.pojo.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
@Query("SELECT * FROM favorite")
    fun getAllFavourite(): Flow<List<Result>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavourite(product: Result)
    @Delete
    suspend fun deletFavourite(product: Result)
}