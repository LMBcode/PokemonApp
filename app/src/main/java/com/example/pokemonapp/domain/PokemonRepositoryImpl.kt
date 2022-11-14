package com.example.pokemonapp.domain

import android.util.Log
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.suspendCoroutine

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : PokemonRepository {


    override suspend fun addPokemon(pokemon: Pokemon) {
        db.collection("pokemons")
            .document(pokemon.name!!)
            .set(pokemon)
            .addOnSuccessListener {  }
    }

    override suspend fun getPokemon(pokemonList : ArrayList<Pokemon>) : List<Pokemon>{
        db.collection("pokemons").addSnapshotListener{
            value,error->
            if (error != null) {
                Log.e("Error", error.message.toString())
            }
            for (dc: DocumentChange in value?.documentChanges!!) {
                if (dc.type == DocumentChange.Type.ADDED) {
                    pokemonList.add(dc.document.toObject(Pokemon::class.java))
                    Log.d("POKELIST",pokemonList.toString())
                }
            }
        }
        return pokemonList
    }
}