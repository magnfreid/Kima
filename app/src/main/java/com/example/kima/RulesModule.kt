package com.example.kima

import android.util.Log
import com.example.kima.models.Player

class RulesModule(viewModel: GameViewModel) : GameLogics {


    private val player = Player("You", viewModel.userCard.value)
    private val computer = Player("Computer", viewModel.userCard.value)
    private var activePlayer = player
    private var winner = activePlayer

    override fun checkWinner() {
        val reactivePlayer = if (activePlayer == player) computer else player
        if (activePlayer.playedCard != null && reactivePlayer.playedCard != null) {
            if (reactivePlayer.playedCard?.suit == activePlayer.playedCard?.suit) {
                if (reactivePlayer.playedCard?.rank!! > activePlayer.playedCard?.rank!!) {
                    winner = reactivePlayer
                }
            }
            //TODO Hantera på bättre sätt?
        } else Log.d(
            "SOUT",
            "Card error: Active player card: ${activePlayer.playedCard} - Reactive player card: ${reactivePlayer.playedCard}"
        )
    }

    override fun resolveTurn() {
        activePlayer = winner
        winner.score++
    }


}

