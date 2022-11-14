package com.example.pokemonapp.domain

import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response

interface PokemonRepository {


      suspend fun addPokemon(pokemon : Pokemon)

      suspend fun getPokemon(pokemonList : ArrayList<Pokemon>) : List<Pokemon>

}