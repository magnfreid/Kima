package com.example.kima.viewmodel

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
    val gameRules = GameRules(player.value, computer.value) { winner ->
        onResolveTurn(winner)
    }


//-------------------------LIVEDATA---------------------------------------------------------------//


    // Livedata for player and computer Player-objects.
    val player: LiveData<Player> get() = playerManager.player
    val computer: LiveData<Player> get() = playerManager.computer

    val scoreBoardCollection: LiveData<MutableList<Scoreboard.ScoreboardRow>> get() = scoreboard.scoreboardScoreCollection

    // LiveData for updating the back-of-card image for computer's played card spot.
    val imageChangeEvent = MutableLiveData<Boolean>()

    // LiveData to govern the trick rounds.
    val trickCounter = MutableLiveData(0)


//------------------------------FUNCTIONS---------------------------------------------------------//


    fun dealPlayerHand() {
        playerManager.updatePlayerHand(deckManager.drawFullHand())
    }

    fun dealComputerHand() {
        playerManager.updateComputerHand(deckManager.drawFullHand())
    }

    fun updatePlayerCard(card: Card) {
        playerManager.updatePlayerPlayedCard(card)
    }

    fun checkWinner(): Player? {
        return gameRules.checkWinner()
    }

    fun resolveTurn() {
        gameRules.resolveTurn()
    }

    fun removePlayedCard(card: Card) {
        playerManager.removePlayerCardFromHand(card)
    }

    /**
     * Updates the winner's score and adds the resolved round's scores to the scoreboard.
     */
    private fun onResolveTurn(winner: Player?) {
        if (winner == player.value) {
            playerManager.incrementPlayerScore()

        } else if (winner == computer.value) {
            playerManager.incrementComputerScore()
        }
        val playerScore = if (winner == player.value) 1 else 0
        val computerScore = if (winner == computer.value) 1 else 0
        val round = 5 - player.value?.hand!!.size
        scoreboard.addScoreboardRow(round, playerScore, computerScore)
    }

    fun randomiseComputerCard(): Card {
        return playerManager.drawComputerRandomCard()
    }

    fun computerReactiveCardPick(): Card {
        return playerManager.drawComputerReactiveCard()
    }

    fun incrementTricks() {
        trickCounter.value = trickCounter.value!! + 1
    }

    fun checkCardPlacementViability(card: Card): Boolean {
        return playerManager.checkCardPlacementViability(card)
    }


}


