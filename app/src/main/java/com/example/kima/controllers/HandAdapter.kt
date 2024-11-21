package com.example.kima.controllers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.kima.R
import com.example.kima.models.Card
import com.google.android.material.button.MaterialButton

class HandAdapter(private val hand: List<Card>) :
    RecyclerView.Adapter<HandAdapter.HandViewHolder>() {

    inner class HandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImageView: ImageView = itemView.findViewById(R.id.iv_hand_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HandViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_card, parent, false)
        return HandViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hand.size
    }

    override fun onBindViewHolder(holder: HandViewHolder, position: Int) {
        val currentCard = hand[position]
        Log.d("SOUT", "${currentCard.id}")
        holder.cardImageView.setImageResource(currentCard.id)
        }
    }
