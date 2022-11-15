package com.example.pokemonapp.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.adapter.PokemonAdapter
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.databinding.FragmentHomeBinding
import com.example.pokemonapp.databinding.PokemondetailFragmentBinding
import com.example.pokemonapp.domain.Constants.POKEMON_ABILITY
import com.example.pokemonapp.domain.Constants.POKEMON_ATTACK
import com.example.pokemonapp.domain.Constants.POKEMON_DEFENSE
import com.example.pokemonapp.domain.Constants.POKEMON_GENDER
import com.example.pokemonapp.domain.Constants.POKEMON_HP
import com.example.pokemonapp.domain.Constants.POKEMON_IMAGE
import com.example.pokemonapp.domain.Constants.POKEMON_NAME
import com.example.pokemonapp.domain.Constants.POKEMON_SIZE
import com.example.pokemonapp.domain.Constants.POKEMON_SPEED
import com.example.pokemonapp.domain.Constants.POKEMON_TYPE
import com.example.pokemonapp.domain.Constants.POKEMON_WEIGHT
import com.example.pokemonapp.presentation.PokemonViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var _binding: PokemondetailFragmentBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = PokemondetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDetails()
        progress()
        pokemonBody()
    }

    private fun pokemonBody(){
        binding.pokemonGender.text = arguments?.getString(POKEMON_GENDER)
        binding.pokemonAbility.text = arguments?.getString(POKEMON_ABILITY)
        binding.weight.text = arguments?.getString(POKEMON_WEIGHT) + " lbs"
        binding.height.text = arguments?.getString(POKEMON_SIZE)
    }

    private fun progress(){
        binding.progressHp.progress = arguments?.getInt(POKEMON_HP)!!.toFloat()
        binding.progressAttack.progress = arguments?.getInt(POKEMON_ATTACK)!!.toFloat()
        binding.progressDefense.progress = arguments?.getInt(POKEMON_DEFENSE)!!.toFloat()
        binding.progressSpeed.progress = arguments?.getInt(POKEMON_SPEED)!!.toFloat()
    }

    private fun addDetails() {
        binding.apply {
            pokemonName.text = arguments?.getString(POKEMON_NAME, "")
            arguments?.getString(POKEMON_IMAGE)?.let {
                Glide.with(this@DetailFragment)
                    .load(it)
                    .into(pokemonImage)
            }
            type.text = arguments?.getString(POKEMON_TYPE, "")
            when (type.text) {
                "GRASS" -> {
                    pokemonImageLayout.setBackgroundColor(Color.parseColor("#348C31"))
                }
                "FIRE" -> {
                    pokemonImageLayout.setBackgroundColor(Color.parseColor("#D21D26"))
                }
                "WATER" -> {
                    pokemonImageLayout.setBackgroundColor(Color.parseColor("#6BC6EF"))
                }
                "BUG" -> {
                    pokemonImageLayout.setBackgroundColor(Color.parseColor("#AAB350"))
                }
                "NORMAL" -> {
                    pokemonImageLayout.setBackgroundColor(Color.parseColor("#BDB9B7"))
                }
                "ELECTRIC" -> {
                    pokemonImageLayout.setBackgroundColor(Color.parseColor("#F9E353"))
                }
                "ICE" -> {
                    pokemonImageLayout.setBackgroundColor(Color.parseColor("#D1EDF2"))
                }
                "POISON" -> {
                    pokemonImageLayout.setBackgroundColor(Color.parseColor("#9A5ABF"))
                }
                else -> {
                    pokemonImageLayout.setBackgroundColor(Color.parseColor("#7E736F"))
                }
            }
            Log.d("IMAGE", POKEMON_IMAGE)
        }


    }
}