package com.example.pokemonapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.adapter.BookmarkAdapter
import com.example.pokemonapp.adapter.PokemonAdapter
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.databinding.FragmentBookmarkBinding
import com.example.pokemonapp.databinding.FragmentHomeBinding
import com.example.pokemonapp.domain.Constants.POKEMON_ABILITY
import com.example.pokemonapp.domain.Constants.POKEMON_ATTACK
import com.example.pokemonapp.domain.Constants.POKEMON_DEFENSE
import com.example.pokemonapp.domain.Constants.POKEMON_DESC
import com.example.pokemonapp.domain.Constants.POKEMON_GENDER
import com.example.pokemonapp.domain.Constants.POKEMON_HP
import com.example.pokemonapp.domain.Constants.POKEMON_IMAGE
import com.example.pokemonapp.domain.Constants.POKEMON_NAME
import com.example.pokemonapp.domain.Constants.POKEMON_POWER
import com.example.pokemonapp.domain.Constants.POKEMON_SIZE
import com.example.pokemonapp.domain.Constants.POKEMON_SPECIE
import com.example.pokemonapp.domain.Constants.POKEMON_SPEED
import com.example.pokemonapp.domain.Constants.POKEMON_TYPE
import com.example.pokemonapp.domain.Constants.POKEMON_WEIGHT
import com.example.pokemonapp.presentation.AuthViewModel
import com.example.pokemonapp.presentation.BookmarkViewModel
import com.example.pokemonapp.presentation.PokemonViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {

    private lateinit var _binding : FragmentBookmarkBinding
    private val binding get() = _binding
    private lateinit var adapter: BookmarkAdapter
    private lateinit var layoutManager : GridLayoutManager
    private lateinit var pokemonList : ArrayList<Pokemon>
    private val bookMarkViewModel : BookmarkViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookmarkBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonList = arrayListOf()
        adapter = BookmarkAdapter(requireContext(),pokemonList)
        layoutManager = GridLayoutManager(activity,2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
        bookMarkViewModel.getAllPokemons.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list)
        }
        deletePokemonFromBookmark()

    }

    private fun deletePokemonFromBookmark(){
        adapter.setOnItemClickListener(object : BookmarkAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                val pokemonList = pokemonList[position]
                bookMarkViewModel.deletePokemon(pokemonList)
            }

        })
    }



    private fun getArgs(pokemon: Pokemon){
        val args = Bundle()
        args.putSerializable(POKEMON_NAME,pokemon.name)
        args.putSerializable(POKEMON_TYPE,pokemon.type)
        args.putSerializable(POKEMON_DESC,pokemon.description)
        args.putSerializable(POKEMON_IMAGE,pokemon.pokemonImage)
        args.putSerializable(POKEMON_SPEED,pokemon.pokemonSpeed)
        args.putSerializable(POKEMON_HP,pokemon.pokemonHp)
        args.putSerializable(POKEMON_ABILITY,pokemon.pokemonAbility)
        args.putSerializable(POKEMON_GENDER,pokemon.pokemonGender)
        args.putSerializable(POKEMON_POWER,pokemon.pokemonPower)
        args.putSerializable(POKEMON_SPECIE,pokemon.pokemonSpecie)
        args.putSerializable(POKEMON_DEFENSE,pokemon.pokemonDefense)
        args.putSerializable(POKEMON_ATTACK,pokemon.pokemonAttack)
        args.putSerializable(POKEMON_SIZE,pokemon.pokemonSize)
        args.putSerializable(POKEMON_WEIGHT,pokemon.pokemonWeight)
        findNavController().navigate(R.id.detailFragment,args)
        Log.d("IMAGE", pokemon.pokemonImage.toString())

    }

}