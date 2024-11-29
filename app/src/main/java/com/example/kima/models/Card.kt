package com.example.kima.models

import android.os.Parcel
import android.os.Parcelable

class Card(val suit: String, val rank: Int, val id: Int, var isRaised: Boolean = false) {

}