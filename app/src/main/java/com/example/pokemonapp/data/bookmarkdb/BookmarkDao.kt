package com.example.pokemonapp.data.bookmarkdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemonapp.data.Pokemon

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM pokemon")
    fun getPokemons() : LiveData<List<Pokemon>>

    @Insert
    suspend fun addPokemonToBookmark(pokemon: Pokemon)

    @Query("DELETE  FROM pokemon")
    fun deleteAll()

    @Delete
    suspend fun deleteFromBookmark(pokemon: Pokemon)


}