package com.example.pokemonapp.domain

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val storage : StorageReference
) : PokemonRepository {


    override suspend fun addImageToStorage(imageUri: Uri) {
        val time = System.currentTimeMillis()
        storage.child(time.toString())
            .putFile(imageUri)
            .await()
            .storage
            .downloadUrl.addOnSuccessListener { }
            .await()

    }







    override suspend fun addPokemon(pokemon: Pokemon) {
        db.collection("pokemons")
            .document(pokemon.name!!)
            .set(pokemon)
            .addOnSuccessListener {  }


    }

    override fun readPokemon(): LiveData<MutableList<Pokemon>> {
        val mutableData = MutableLiveData<MutableList<Pokemon>>()
        val pokemonList = mutableListOf<Pokemon>()
        db.collection("pokemons").get().addOnSuccessListener {
            for (document in it) {
                val pokemons = document.toObject(Pokemon::class.java)
                pokemonList.add(pokemons)
            }
            mutableData.value = pokemonList
        }
        return mutableData
    }



}