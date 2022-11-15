package com.example.pokemonapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.LoginFragmentBinding
import com.example.pokemonapp.databinding.PokemondetailFragmentBinding
import com.example.pokemonapp.databinding.RegisterFragmentBinding
import com.example.pokemonapp.presentation.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var _binding: LoginFragmentBinding
    private val binding get() = _binding
    private val viewModel : AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.currentUser == null) {
            binding.cirLoginButton.setOnClickListener {
                if (binding.editTextEmail.text.isNotEmpty() && binding.editTextPassword.text.isNotEmpty()) {
                    signIn(
                        binding.editTextEmail.text.toString(),
                        binding.editTextPassword.text.toString()
                    )
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
                }
            }
        }else{findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)}
        binding.signup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    private fun signIn(email : String,password:String){
        viewModel.loginUser(email,password)
    }
}