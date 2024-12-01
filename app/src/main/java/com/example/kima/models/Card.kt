package com.example.kima.models

data class Card(val suit: String, val rank: Int, val id: Int, var isRaised: Boolean = false)