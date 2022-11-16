package com.example.pokemonapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.data.Pokemon
import com.example.pokemonapp.databinding.PokemonitemBinding
import java.io.IOException

class BookmarkAdapter(private val context: Context, private val pokemonList : ArrayList<Pokemon>) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {


    private lateinit var mListener : OnItemClickListener
    interface OnItemClickListener{
        fun onClick(position: Int)
    }

    fun setOnItemClickListener(listener : OnItemClickListener){
        mListener = listener
    }
    class ViewHolder(val binding : PokemonitemBinding, listener : OnItemClickListener) : RecyclerView.ViewHolder(binding.root){
            init {
                binding.bookmark.setOnClickListener {
                    listener.onClick(adapterPosition)
                }
            }

        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkAdapter.ViewHolder {
        val binding = PokemonitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkAdapter.ViewHolder(binding,mListener)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: BookmarkAdapter.ViewHolder, position: Int) {
        val current = pokemonList[position]
        holder.binding.name.text = current.name
        holder.binding.type.text = current.type
        holder.binding.bookmark.setImageResource(R.drawable.ic_baseline_remove_circle_outline_24)
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
            "FAIRY" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#9E1A44"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#9E1A44"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#9E1A44"))
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
            else -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#7E736F"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#7E736F"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#7E736F"))
            }
        }
        Glide.with(context)
            .load(current.pokemonImage)
            .into(holder.binding.image)
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
            }catch (e:IOException){
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun updateList(newList :List<Pokemon>){
        pokemonList.clear()
        pokemonList.addAll(newList).also {
            notifyItemInserted(itemCount-1)
        }
    }
}