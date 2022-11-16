package com.example.pokemonapp.fragments

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
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
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPokemonFragment : Fragment() {

    private val viewModel: PokemonViewModel by viewModels()
    private lateinit var _binding: FragmentAddpokemonBinding
    private val binding get() = _binding
    private val firebaseStorage = FirebaseStorage.getInstance().reference
    var imageUris: MutableList<Uri> = arrayListOf()
    var imageUri: Uri? = null

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            if (resultCode == Activity.RESULT_OK) {
                imageUri = data?.data!!
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Log.d("ImageError", ImagePicker.getError(data))
            } else {
                Log.e("TASK", "Task Cancelled")
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddpokemonBinding.inflate(inflater, container, false)
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
        Log.d("image uri", imageUri.toString())
        Log.d("image uri2", imageUris.toString())
        spinner(binding.gender)
        Log.d("Gender", binding.gender.selectedItem.toString())
        binding.apply {
            seekBar(attack, attackNumber)
            seekBar(defense, defenseNumber)
            seekBar(hp, hpNumber)
            seekBar(speed, speedNumber)
        }
        Log.d("attackP", binding.attackNumber.text.toString())
        binding.button.setOnClickListener {
            uploadImage()
            findNavController().navigate(R.id.action_addPokemonFragment2_to_homeFragment2)
        }

    }

    private fun seekBar(seekBar: SeekBar, text: TextView) {
        seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                // write custom code for progress is changed
                text.text = seek.progress.toString()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })
    }


    private fun uploadImage() {

        val timeStamp = System.currentTimeMillis()
        // Had a hard time having to use it with MVVM this one.
        // Decided to put it here while i'm finding ways to do it .
        val ref = firebaseStorage.child(timeStamp.toString())
        ref.putFile(imageUri!!).continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                Log.d("uri", downloadUri.toString())
                viewModel.addPokemon(
                    Pokemon(
                        id = binding.number.text.toString().toInt(),
                        name = binding.name.text.toString(),
                        description = binding.desc.text.toString(),
                        type = binding.type.text.toString().uppercase(),
                        pokemonImage = downloadUri.toString(),
                        pokemonAbility = binding.ability.text.toString(),
                        pokemonAttack = binding.attackNumber.text.toString().toInt(),
                        pokemonDefense = binding.defenseNumber.text.toString().toInt(),
                        pokemonHp = binding.attackNumber.text.toString().toInt(),
                        pokemonPower = binding.power.text.toString(),
                        pokemonSize = binding.size.text.toString(),
                        pokemonWeight = binding.weight.text.toString(),
                        pokemonSpeed = binding.speedNumber.text.toString().toInt(),
                        pokemonSpecie = binding.species.text.toString(),
                        pokemonGender = binding.gender.selectedItem.toString()
                    )
                )
            }

        }
    }

    private fun spinner(spinner: Spinner) {
        val sex = resources.getStringArray(R.array.Sex)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, sex
        )
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                getString(R.string.selected_item) + " " +
                        "" + sex[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
}
