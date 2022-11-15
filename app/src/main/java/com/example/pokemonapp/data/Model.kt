package com.example.pokemonapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey
    val name : String = "",
    val description : String ?= null,
    val type : String ?= null,
    val pokemonImage : String ?= null,
    val pokemonAbility : String ?= null,
    val pokemonPower : String ?= null,
    val pokemonHp : Int ?= null,
    val pokemonAttack : Int ?=null,
    val pokemonDefense : Int?=null,
    val pokemonSpeed : Int ?= null,
    val pokemonSpecie : String ?= null,
    val pokemonWeight : String ? = null,
    val pokemonSize : String ?= null,
    val pokemonGender : String ? = null
    )
