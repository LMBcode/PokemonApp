package com.example.pokemonapp.data

import android.net.Uri

data class Pokemon(
    val name : String ?= null,
    val description : String ?= null,
    val type : String ?= null,
    val image : Uri ?= null
    )
