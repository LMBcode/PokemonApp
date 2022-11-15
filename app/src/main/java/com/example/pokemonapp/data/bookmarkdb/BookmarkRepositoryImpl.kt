package com.example.pokemonapp.data.bookmarkdb

import androidx.lifecycle.LiveData
import com.example.pokemonapp.data.Pokemon
import javax.inject.Inject


class BookmarkRepositoryImpl@Inject constructor(private val bookmarkDao: BookmarkDao) : BookmarkRepository {


    override val getAllPokemon: LiveData<List<Pokemon>> = bookmarkDao.getPokemons()

    override suspend fun deletePokemon(pokemon: Pokemon) {
        bookmarkDao.deleteFromBookmark(pokemon)
    }

    override suspend fun insertPokemon(pokemon: Pokemon) {
        bookmarkDao.addPokemonToBookmark(pokemon)
    }

    override fun deleteAllPokemons() {
        bookmarkDao.deleteAll()
    }


}