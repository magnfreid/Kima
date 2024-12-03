package com.example.kima.viewmodel


import com.example.kima.models.Player

class GameRules(
    private val player: Player?,
    private val computer: Player?,
    private val onResolveTurn: (winner: Player?) -> Unit
) {

    private var activePlayer = player
    var winner: Player? = player

    fun checkWinner(): Player? {
        winner = activePlayer
        val reactivePlayer = if (activePlayer == player) computer else player
        if (activePlayer?.playedCard != null && reactivePlayer?.playedCard != null) {
            if (reactivePlayer.playedCard?.suit == activePlayer!!.playedCard?.suit) {
                if (reactivePlayer.playedCard?.rank!! > activePlayer!!.playedCard?.rank!!) {
                    winner = reactivePlayer
                }
            }
        }
        activePlayer = winner
        return winner
    }

    fun resolveTurn() {
        onResolveTurn(winner)
    }
}

