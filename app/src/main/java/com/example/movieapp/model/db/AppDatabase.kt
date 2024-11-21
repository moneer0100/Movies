package com.example.movieapp.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.model.pojo.Convertor

import com.example.movieapp.model.pojo.Result



@Database(entities = [Result::class], version = 1)
@TypeConverters(Convertor::class)
abstract class AppDatabase :RoomDatabase () {
    abstract fun movieApp():Dao
}
object DatabaseClient{

    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "favoriteMovies_db",
            ).build()
                .also { instance = it }

        }}}