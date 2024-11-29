package com.example.kima.models

data class Player(val name: String, var playedCard: Card?) {
    var score: Int = 0
}