package com.example.pokemonapp.domain

import android.net.Uri
import android.util.Log
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import com.google.firebase.FirebaseException
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

    override suspend fun addImageToStorage(imageUri: Uri, onResult: (Response<Uri>) -> Unit) {
        try {
            val uri : Uri  = withContext(Dispatchers.IO) {
                storage.child("pokemonImg")
                    .putFile(imageUri)
                    .await()
                    .storage
                    .downloadUrl
                    .await()
            }
            onResult.invoke(Response.Success(uri))
        }catch(e:FirebaseException) {
            onResult.invoke(Response.Failure(e.message))
        }
        catch(e:Exception) {
            onResult.invoke(Response.Failure(e.message))
        }
    }


    override suspend fun addPokemon(pokemon: Pokemon) {
        db.collection("pokemons")
            .document(pokemon.name!!)
            .set(pokemon)
            .addOnSuccessListener {  }
    }

    override fun readPokemon() {
        val pokemonList = arrayListOf<Pokemon>()
        db.collection("pokemons").get().addOnSuccessListener {
            for (document in it) {
                val pokemons = document.toObject(Pokemon::class.java)
                pokemonList.add(pokemons)
            }
        }
    }
}