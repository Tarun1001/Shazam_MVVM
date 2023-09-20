package com.example.ferrari.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ferrari.Utils.TrackT
import com.example.ferrari.databinding.CardCellBinding

class CardAdapter(private val tracks: MutableList<TrackT>)
    : RecyclerView.Adapter<CardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : CardViewHolder {
        val from = LayoutInflater.from(parent.context)

        val binding= CardCellBinding.inflate(from,parent,false)


        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int =tracks.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindSong(tracks[position])
    }
}