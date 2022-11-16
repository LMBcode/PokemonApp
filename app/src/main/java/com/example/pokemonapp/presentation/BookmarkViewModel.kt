package com.example.pokemonapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.bookmarkdb.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookmarkViewModel@Inject constructor(private val bookmarkRepository: BookmarkRepository) : ViewModel(){

    val getAllPokemons : LiveData<List<Pokemon>> = bookmarkRepository.getAllPokemon


    private val _isChecked = MutableLiveData<Boolean>()
    val isChecked : LiveData<Boolean> get() = _isChecked

    fun insertPokemon(pokemon: Pokemon){
        viewModelScope.launch {
            bookmarkRepository.insertPokemon(pokemon)
        }
    }

    fun deletePokemon(pokemon: Pokemon){
        viewModelScope.launch {
            bookmarkRepository.deletePokemon(pokemon)
        }
    }


}