package com.example.pokemonapp.data.bookmarkdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemonapp.data.Pokemon


@Database(entities = [Pokemon::class], version = 2)
abstract class BookmarkDatabase : RoomDatabase(){
        abstract fun bookmarkDao() : BookmarkDao
}