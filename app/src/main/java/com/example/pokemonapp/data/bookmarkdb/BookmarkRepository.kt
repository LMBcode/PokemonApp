package com.example.pokemonapp.data.bookmarkdb

import androidx.lifecycle.LiveData
import com.example.pokemonapp.data.Pokemon


interface BookmarkRepository {

     val getAllPokemon : LiveData<List<Pokemon>>

    suspend fun deletePokemon(pokemon: Pokemon)

    suspend fun insertPokemon(pokemon: Pokemon)

     fun deleteAllPokemons()
}