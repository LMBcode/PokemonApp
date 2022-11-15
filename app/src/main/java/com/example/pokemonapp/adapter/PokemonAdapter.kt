package com.example.pokemonapp.adapter

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.databinding.FragmentAddpokemonBinding
import com.example.pokemonapp.databinding.PokemonitemBinding

class PokemonAdapter(private val context : Context, private var pokemon : ArrayList<Pokemon>, private val onPokemonClick : (pokemon:Pokemon) -> Unit) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(),Filterable {

    private lateinit var mListener : OnItemClickListener
    interface OnItemClickListener{
        fun onClick(position: Int)
    }

    fun setOnItemClickListener(listener : OnItemClickListener){
        mListener = listener
    }

    var pokemonFilter = pokemon

    fun setDataFilter(){
        this.pokemonFilter = pokemon
    }
    class ViewHolder(val binding : PokemonitemBinding,listener : OnItemClickListener) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.bookmark.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PokemonitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = pokemon[position]
        holder.binding.name.text = current.name
        holder.binding.type.text = current.type
        when(current.type){
            "GRASS" -> {holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#348C31"))}
            "FIRE" -> {holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#D21D26")) }
            "WATER" -> {holder.binding.cardView.setCardBackgroundColor(Color.parseColor("##6BC6EF"))}
            "BUG" -> {holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#AAB350"))}
            "NORMAL" -> {holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#BDB9B7"))}
            "ELECTRIC" -> {holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#F9E353"))}
            "ICE" -> {holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#D1EDF2"))}
            "POISON" -> {holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#9A5ABF"))}
            else -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#7E736F"))
            }
        }
        Glide.with(context)
            .load(current.pokemonImage)
            .into(holder.binding.image)
        fun bind(pokemon : Pokemon){
            holder.binding.cardView.setOnClickListener { onPokemonClick.invoke(pokemon) }
        }
        holder.apply { bind(current) }
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if(constraint.isNullOrEmpty()){
                    filterResults.count = pokemonFilter.size
                    filterResults.values = pokemonFilter
                }
                else{
                    val searchLetter = constraint.toString().lowercase()
                    val itemEx = ArrayList<Pokemon>()
                    for(item in pokemonFilter){
                        if(item.name!!.lowercase().contains(searchLetter)){
                            itemEx.add(item)
                        }
                    }
                    filterResults.count = itemEx.size
                    filterResults.values = itemEx
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                pokemon = results?.values as ArrayList<Pokemon>
                notifyDataSetChanged()
            }

        }
    }


}