package com.example.kima.models

data class Player(val name: String) {
    var hand: MutableList<Card>? = null
    var playedCard: Card? = null
    var score: Int = 0
}