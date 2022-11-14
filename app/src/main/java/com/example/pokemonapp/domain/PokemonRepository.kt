package com.example.pokemonapp.domain

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {


    suspend fun addImageToStorage(imageUri : Uri)

      suspend fun addPokemon(pokemon : Pokemon)

       fun readPokemon()

}