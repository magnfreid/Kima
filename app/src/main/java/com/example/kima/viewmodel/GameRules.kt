package com.example.kima.viewmodel


import com.example.kima.models.Player

class GameRules(
    private val player: Player?,
    private val computer: Player?,
    private val onResolveTurn: (winner: Player?) -> Unit
) {

    private var activePlayer = player
    var winner: Player? = player

    /**
     * Checks which player has won a round. Sets the starting player for next round to the winner.
     * @return Returns the winner.
     */
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

    /**
     * Used for resolving each round after a winner is determined.
     */
    fun resolveTurn() {
        onResolveTurn(winner)
    }
}

