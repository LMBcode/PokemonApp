package com.example.pokemonapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import com.example.pokemonapp.databinding.FragmentAddpokemonBinding
import com.example.pokemonapp.presentation.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class AddPokemonFragment : Fragment() {

    private val viewModel : PokemonViewModel by viewModels()
    private lateinit var _binding : FragmentAddpokemonBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddpokemonBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            addPokemon()
            findNavController().navigate(R.id.action_addPokemonFragment2_to_homeFragment2)
        }

    }

    private fun addPokemon(){
        viewModel.addPokemon(
            Pokemon(binding.name.text.toString(),
                binding.desc.text.toString(),
                binding.type.text.toString())

        )
    }
}