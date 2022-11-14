package com.example.pokemonapp.data

sealed class Response<out T> {
    object Loading: Response<Nothing>()

    data class Success<out T>(
        val data: T
    ): Response<T>()

    data class Failure(
        val e: String?
    ): Response<Nothing>()
}
