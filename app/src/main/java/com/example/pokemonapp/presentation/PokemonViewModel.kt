package com.example.pokemonapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import com.example.pokemonapp.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repo: PokemonRepository
): ViewModel() {



    fun addPokemon(pokemon: Pokemon){
        viewModelScope.launch {
            repo.addPokemon(pokemon)
        }
    }

    fun getPokemon(pokemon: ArrayList<Pokemon>){
        viewModelScope.launch {
            repo.getPokemon(pokemon)
        }
    }

}