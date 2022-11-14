package com.example.pokemonapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.databinding.FragmentAddpokemonBinding
import com.example.pokemonapp.databinding.PokemonitemBinding

class PokemonAdapter(private val pokemon : ArrayList<Pokemon>,private val onPokemonClick : (pokemon:Pokemon) -> Unit) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {



    class ViewHolder(val binding : PokemonitemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PokemonitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = pokemon[position]
        holder.binding.name.text = current.name
        holder.binding.type.text = current.type
        fun bind(pokemon : Pokemon){
            holder.binding.cardView.setOnClickListener { onPokemonClick.invoke(pokemon) }
        }
        holder.apply { bind(current) }
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }




}