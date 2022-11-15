package com.example.firebaseAuth

import com.example.pokemonapp.data.Resource
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}