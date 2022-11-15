package com.example.pokemonapp.di

import android.content.Context
import androidx.room.Room
import com.example.pokemonapp.data.bookmarkdb.BookmarkDatabase
import com.example.pokemonapp.data.bookmarkdb.BookmarkRepository
import com.example.pokemonapp.data.bookmarkdb.BookmarkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideBookmarkDao(appDatabase: BookmarkDatabase)  = appDatabase.bookmarkDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            BookmarkDatabase::class.java,
            "BookmarkPokemon"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun shoeRepository(db : BookmarkDatabase) : BookmarkRepository {
        return BookmarkRepositoryImpl(db.bookmarkDao())
    }
}