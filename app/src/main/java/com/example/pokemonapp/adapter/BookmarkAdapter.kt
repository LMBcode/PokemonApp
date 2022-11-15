package com.example.pokemonapp.adapter

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

    override fun onBindViewHolder(holder: BookmarkAdapter.ViewHolder, position: Int) {
        val current = pokemonList[position]
        holder.binding.name.text = current.name
        holder.binding.type.text = current.type
        holder.binding.bookmark.setImageResource(R.drawable.ic_baseline_remove_circle_outline_24)
        when(current.type){
            "GRASS" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#348C31"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#348C31"))
            }
            "FIRE" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#D21D26"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#D21D26"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#D21D26"))
            }
            "WATER" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#6BC6EF"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#6BC6EF"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#6BC6EF"))
            }
            "BUG" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#AAB350"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#AAB350"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#AAB350"))
            }
            "NORMAL" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#BDB9B7"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#BDB9B7"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#BDB9B7"))
            }
            "ELECTRIC" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#F9E353"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#F9E353"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#F9E353"))

            }
            "ICE" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#D1EDF2"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#D1EDF2"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#D1EDF2"))
            }
            "POISON" -> {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#9A5ABF"))
                holder.binding.audio.setBackgroundColor(Color.parseColor("#9A5ABF"))
                holder.binding.bookmark.setBackgroundColor(Color.parseColor("#9A5ABF"))
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
        pokemonList.addAll(newList)
        notifyDataSetChanged()
    }
}