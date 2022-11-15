package com.example.pokemonapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.PokemondetailFragmentBinding
import com.example.pokemonapp.databinding.RegisterFragmentBinding
import com.example.pokemonapp.presentation.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var _binding: RegisterFragmentBinding
    private val binding get() = _binding
    private val viewModel : AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            if (binding.name.text.isNotEmpty() && binding.email.text.isNotEmpty()&&binding.password.text.isNotEmpty()) {
                register(
                    binding.name.text.toString(),
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
                findNavController().navigate(R.id.action_registerFragment_to_homeFragment2)
            }

        }
        binding.signin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }

    private fun register(name : String ,email : String,password:String){
        viewModel.signupUser(name = name,email, password)
    }
}