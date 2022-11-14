package com.example.pokemonapp.di

import com.example.pokemonapp.domain.PokemonRepository
import com.example.pokemonapp.domain.PokemonRepositoryImpl
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage() : FirebaseStorage{
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideRepositorty(db : FirebaseFirestore,storage : FirebaseStorage) : PokemonRepository{
        return PokemonRepositoryImpl(db,storage)
    }


}