package com.example.pokemonapp.domain

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import com.google.firebase.firestore.auth.User

interface PokemonRepository {


    suspend fun addImageToStorage(imageUri : Uri)

      suspend fun addPokemon(pokemon : Pokemon)

       fun readPokemon() : LiveData<MutableList<Pokemon>>


}