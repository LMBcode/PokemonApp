package com.example.pokemonapp.domain

import android.net.Uri
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import com.google.firebase.firestore.auth.User

interface PokemonRepository {


    suspend fun addImageToStorage(imageUri : Uri,onResult :(Response<Uri>) -> Unit)

      suspend fun addPokemon(pokemon : Pokemon)

       fun readPokemon()

}