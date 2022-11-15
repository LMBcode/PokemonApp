package com.example.pokemonapp.fragments

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.data.Response
import com.example.pokemonapp.databinding.FragmentAddpokemonBinding
import com.example.pokemonapp.presentation.PokemonViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPokemonFragment : Fragment() {

    private val viewModel : PokemonViewModel by viewModels()
    private lateinit var _binding : FragmentAddpokemonBinding
    private val binding get() = _binding
    private val objPokemon : Pokemon?=null
    var imageUris: MutableList<Uri> = arrayListOf()
    var imageUri: Uri ?= null

    private val startForProfileImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        val resultCode = result.resultCode
        val data = result.data
        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data!!
            imageUris.add(fileUri)
            imageUri = fileUri
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Log.d("ImageError",ImagePicker.getError(data))
        } else {
            Log.e("TASK","Task Cancelled")
        }
    }
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
        binding.image.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .galleryOnly()
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
        binding.button.setOnClickListener {
            uploadImage()
            findNavController().navigate(R.id.action_addPokemonFragment2_to_homeFragment2)
        }

    }

  /*  private fun getImageUrls(): List<String> {
        if (imageUris.isNotEmpty()){
            return imageUris.map { it.toString() }
        }else{
            return objPokemon?.images ?: arrayListOf()
        }
    }*/

    private fun addPokemon(){
        viewModel.addPokemon(
            Pokemon(name = binding.name.text.toString(),
                description = binding.desc.text.toString(),
                type= binding.type.text.toString(),
                pokemonImage = imageUri.toString()
            )
        )
    }

    private fun uploadImage(){
        if(imageUris.isNotEmpty()){
            viewModel.addImageToStorage(imageUris.first()){state->
                when(state){
                    is Response.Loading ->{
                        Toast.makeText(activity,"LOADING",Toast.LENGTH_SHORT).show()
                    }
                    is Response.Failure ->{ Toast.makeText(activity,"FAILED",Toast.LENGTH_SHORT).show()
                        Log.d("RESPONSE",state.e.toString())
                    }

                    is Response.Success ->{
                        Toast.makeText(activity,"SUCCESS",Toast.LENGTH_SHORT).show()
                        viewModel.addPokemon(
                            Pokemon(name = binding.name.text.toString(),
                               description =  binding.desc.text.toString(),
                               type=  binding.type.text.toString(),
                                pokemonImage = imageUri.toString(),
                                pokemonAbility = binding.ability.text.toString(),
                                pokemonAttack = binding.attack.text.toString().toInt(),
                                pokemonDefense = binding.defense.text.toString().toInt(),
                                pokemonHp = binding.hp.text.toString().toInt(),
                                pokemonPower = binding.power.text.toString(),
                                pokemonSize = binding.size.text.toString(),
                                pokemonWeight = binding.weight.text.toString(),
                                pokemonSpeed = binding.speed.text.toString().toInt(),
                                pokemonSpecie = binding.species.text.toString(),
                                pokemonGender = binding.gender.text.toString()
                            )
                        )
                    }
                }

            }
        }
    }
}