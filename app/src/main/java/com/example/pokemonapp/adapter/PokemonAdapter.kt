package com.example.pokemonapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.databinding.FragmentAddpokemonBinding
import com.example.pokemonapp.databinding.PokemonitemBinding
import com.example.pokemonapp.presentation.BookmarkViewModel
import java.io.IOException

class PokemonAdapter(private val context : Context, private var pokemon : MutableList<Pokemon>, private val onPokemonClick : (pokemon:Pokemon) -> Unit) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(),Filterable {
    private lateinit var mListener : OnItemClickListener
    interface OnItemClickListener{
        fun onClick(position: Int)
    }

    fun setOnItemClickListener(listener : OnItemClickListener){
        mListener = listener
    }
    fun updateList(list : MutableList<Pokemon>){
        this.pokemon = list
    }
    var pokemonFilter = pokemon

    class ViewHolder(val binding : PokemonitemBinding,listener : OnItemClickListener) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.bookmark.setOnClickListener {
                binding.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = pokemon[position]
        holder.binding.name.text = current.name
        holder.binding.type.text = current.type
        if (current.isSaved){
            holder.binding.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
        }

        when(current.type){
            "GRASS" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#007C42"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#007C42"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#007C42"))
            }
            "FIRE" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#B22328"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#B22328"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#B22328"))
            }
            "WATER" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#2648DC"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#2648DC"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#2648DC"))
            }
            "BUG" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#179A55"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#179A55"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#179A55"))
            }
            "NORMAL" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#BDB9B7"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#BDB9B7"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#BDB9B7"))
            }
            "ELECTRIC" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#E0E64B"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#E0E64B"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#E0E64B"))

            }
            "ICE" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#7ECFF2"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#7ECFF2"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#7ECFF2"))
            }
            "POISON" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("642785"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("642785"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("642785"))
            }
            "FAIRY" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#9E1A44"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#9E1A44"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#9E1A44"))
            }
            else -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#7E736F"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#7E736F"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#7E736F"))
            }
        }
        holder.binding.audio.setOnClickListener {
            var url = "https://play.pokemonshowdown.com/audio/cries/pikachu.mp3"
            when(current.name){
                "Pikachu" ->{url = "https://play.pokemonshowdown.com/audio/cries/pikachu.mp3" }
                "Charmander" -> {url ="https://play.pokemonshowdown.com/audio/cries/charmander.mp3"}
                "Bulbasaur" ->{url = "https://play.pokemonshowdown.com/audio/cries/bulbasaur.mp3"}
                "Caterpie" ->{url ="https://play.pokemonshowdown.com/audio/cries/caterpie.mp3"}
                "Squirtle" -> {url = "https://play.pokemonshowdown.com/audio/cries/squirtle.mp3"}
                "Jigglypuff" -> {url = "https://play.pokemonshowdown.com/audio/cries/jigglypuff.mp3"}
                "Charizard" -> {url = "https://play.pokemonshowdown.com/audio/cries/charizard-megay.mp3"}
            }
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            try{
                mediaPlayer.setDataSource(url)
                mediaPlayer.prepare()
                mediaPlayer.start()
            }catch (e: IOException){
                e.printStackTrace()
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