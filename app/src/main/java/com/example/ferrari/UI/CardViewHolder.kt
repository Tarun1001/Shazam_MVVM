package com.example.ferrari.UI

import androidx.recyclerview.widget.RecyclerView
import com.example.ferrari.Utils.TrackT
import com.example.ferrari.databinding.CardCellBinding

class CardViewHolder(
    private val cardCellBinding: CardCellBinding )
    :RecyclerView.ViewHolder(cardCellBinding.root){
        fun bindSong(track: TrackT){
            cardCellBinding.title.text=track.title
        }
}