package com.example.pokemonapp.domain

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.suspendCoroutine

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val storage : FirebaseStorage
) : PokemonRepository {

    override suspend fun addImageToStorage(imageUri: Uri) {
         storage.reference.child("pokemonImg")
            .putFile(imageUri).await()
            .storage.downloadUrl.await()
    }


    override suspend fun addPokemon(pokemon: Pokemon) {
        db.collection("pokemons")
            .document(pokemon.name!!)
            .set(pokemon)
            .addOnSuccessListener {  }
    }

    override fun readPokemon() {
        val pokemonList : ArrayList<Pokemon> = arrayListOf()
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
    }
}