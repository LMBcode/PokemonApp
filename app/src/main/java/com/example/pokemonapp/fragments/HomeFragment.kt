package com.example.pokemonapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.adapter.PokemonAdapter
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.databinding.FragmentHomeBinding
import com.example.pokemonapp.domain.Constants.POKEMON_DESC
import com.example.pokemonapp.domain.Constants.POKEMON_NAME
import com.example.pokemonapp.domain.Constants.POKEMON_TYPE
import com.example.pokemonapp.presentation.PokemonViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding : FragmentHomeBinding
    private val binding get() = _binding
    private lateinit var adapter: PokemonAdapter
    private lateinit var layoutManager : GridLayoutManager
    private lateinit var pokemonList : ArrayList<Pokemon>
    private val db = FirebaseFirestore.getInstance()
    private val viewModel : PokemonViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonList = arrayListOf()
        adapter = PokemonAdapter((pokemonList)){pokemon -> getArgs(pokemon)}
        layoutManager = GridLayoutManager(activity,2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
        database()

    }

    private fun database(){
        db.collection("pokemons").addSnapshotListener{
                value,error->
            if (error != null) {
                Log.e("Error", error.message.toString())
            }
            for (dc: DocumentChange in value?.documentChanges!!) {
                if (dc.type == DocumentChange.Type.ADDED) {
                    pokemonList.add(dc.document.toObject(Pokemon::class.java))
                    Log.d("POKELIST",pokemonList.toString())
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun getArgs(pokemon: Pokemon){
        val args = Bundle()
        args.putSerializable(POKEMON_NAME,pokemon.name)
        args.putSerializable(POKEMON_TYPE,pokemon.type)
        args.putSerializable(POKEMON_DESC,pokemon.description)
        findNavController().navigate(R.id.detailFragment,args)
    }

}