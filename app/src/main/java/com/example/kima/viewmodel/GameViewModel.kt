package com.example.kima.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kima.models.Card
import com.example.kima.models.DeckManager
import com.example.kima.models.Player
import com.example.kima.models.PlayerManager
import com.example.kima.models.Scoreboard


class GameViewModel : ViewModel() {

    private val deckManager = DeckManager()
    private val playerManager = PlayerManager()
    private val scoreboard = Scoreboard()
    private val gameRules = GameRules(player.value, computer.value) { winner ->
        onResolveTurn(winner)
    }


//-------------------------LIVEDATA---------------------------------------------------------------//


    val player: LiveData<Player> get() = playerManager.player
    val computer: LiveData<Player> get() = playerManager.computer

    val scoreBoardCollection: LiveData<MutableList<Scoreboard.ScoreboardRow>> get() = scoreboard.scoreBoardScoreCollection

    val _startNextTrick = MutableLiveData<Boolean>(true)
    val startNextTrick: LiveData<Boolean> get() = _startNextTrick

    private var trickCounter = 0

//------------------------------FUNCTIONS---------------------------------------------------------//



    fun dealPlayerHand() {
        playerManager.updatePlayerHand(deckManager.drawFullHand())
    }

    fun dealComputerHand() {
        playerManager.updateComputerHand(deckManager.drawFullHand())
    }

    fun updatePlayerCard(card: Card) {
        playerManager.updateUserPlayedCard(card)
    }

    fun checkWinner(): Player? {
        return gameRules.checkWinner()
    }

    fun resolveTurn() {
        gameRules.resolveTurn()
    }

    fun removePlayedCard(card : Card) {
        playerManager.removePlayedCard(card)
    }

    private fun onResolveTurn(winner: Player?) {
        if (winner == player.value) {
            playerManager.incrementPlayerScore()
        } else if (winner == computer.value) {
            playerManager.incrementComputerScore()
        }
        val playerScore = player.value?.score ?: 0
        val computerScore = computer.value?.score ?: 0
        val round = 6 - player.value?.hand!!.size
        scoreboard.addScoreboardRow(round, playerScore, computerScore)
        Log.d("SOUT", "${scoreBoardCollection.value}")
    }

    fun randomiseComputerCard(): Card {
        return playerManager.randomiseComputerCard()
    }

    fun resetTrick() {
//        playerManager.resetPlayedCards()
        trickCounter++
        if(trickCounter <5) {
            _startNextTrick.value = true
        } else {
            // TODO: End game logic!
        }
    }

    fun startNextTrickConsumed() {
        _startNextTrick.value = false // Reset the trigger
    }


}


