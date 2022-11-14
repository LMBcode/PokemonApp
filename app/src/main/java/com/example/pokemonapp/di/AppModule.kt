package com.example.pokemonapp.di

import com.example.pokemonapp.domain.FirebaseStorageConstants
import com.example.pokemonapp.domain.PokemonRepository
import com.example.pokemonapp.domain.PokemonRepositoryImpl
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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
    fun provideFirebaseStorage() : StorageReference{
        return FirebaseStorage.getInstance().getReference(FirebaseStorageConstants.ROOT_DIRECTORY)
    }

    @Provides
    @Singleton
    fun provideRepositorty(db : FirebaseFirestore,storage : StorageReference) : PokemonRepository{
        return PokemonRepositoryImpl(db,storage)
    }


}