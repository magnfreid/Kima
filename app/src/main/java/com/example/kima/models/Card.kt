package com.example.kima.models

import android.os.Parcel
import android.os.Parcelable

class Card(val suit: String, val rank: Int, val id: Int, var isRaised: Boolean = false) {
    companion object{
        const val HEARTS = "hearts"
        const val CLUBS = "clubs"
        const val DIAMONDS = "diamonds"
        const val SPADES = "spades"
    }

}