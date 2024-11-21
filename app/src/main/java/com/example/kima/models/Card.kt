package com.example.kima.models

import android.os.Parcel
import android.os.Parcelable

class Card(val suit: String, val rank: Int, val id: Int, val imageName: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(suit)
        parcel.writeInt(rank)
        parcel.writeInt(id)
        parcel.writeString(imageName)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}