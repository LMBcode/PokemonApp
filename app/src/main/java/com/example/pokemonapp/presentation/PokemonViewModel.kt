package com.example.pokemonapp.presentation

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import com.example.pokemonapp.domain.PokemonRepository
import com.google.firebase.firestore.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repo: PokemonRepository
): ViewModel() {

    private val _pokemon = MutableLiveData<Response<List<Pokemon>>>()
    val pokemon : LiveData<Response<List<Pokemon>>> get() =  _pokemon


    fun addPokemon(pokemon: Pokemon){
        viewModelScope.launch {
            repo.addPokemon(pokemon)
        }
    }

    fun addImageToStorage(imageUri: Uri,onResult : (Response<Uri>) -> Unit){
        onResult.invoke(Response.Loading)
        viewModelScope.launch {
            repo.addImageToStorage(imageUri,onResult)
        }
    }

    fun getPokemon() {
        _pokemon.value = Response.Loading
        repo.readPokemon()
    }


}