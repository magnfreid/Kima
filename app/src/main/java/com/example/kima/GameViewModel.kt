package com.example.kima

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kima.models.Card
import com.example.kima.models.DeckManager
import com.example.kima.models.Player
import com.example.kima.models.ScoreManager

/*class GameViewModel : ViewModel(), GameLogics {


    private val deckManager = DeckManager()
    private val rulesModule: GameLogics = RulesModule(this)

    // LiveData for score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    // LiveData for user's hand
    val userHand: LiveData<MutableList<Card>> get() = deckManager.userHand

    // LiveData for computer's played card

    val userCard: LiveData<Card> get() = deckManager.userCard

    // LiveData for computer's played card

    val computerCard: LiveData<Card> get() = deckManager.computerCard

    val winner: LiveData<Player> get() = rulesModule.winner

    fun updatePlayerCard(card: Card) {
        deckManager.updatePlayerCard(card)

    }

    override fun checkWinner() {
        rulesModule.checkWinner()
    }

    override fun resolveTurn() {
        rulesModule.resolveTurn()
    }
    fun checkWinner() {
        rulesModule.checkWinner()
    }
    fun resolveTurn() {
        rulesModule.resolveTurn()
    }

    fun randomiseComputerCard() : Card {
        return deckManager.randomiseComputerCard()
    }


}


interface GameLogics {
    fun checkWinner()
    fun resolveTurn()
}*/


class GameViewModel : ViewModel() {

    private val deckManager = DeckManager()
    private val rulesModule = RulesModule(this)
    private val scoreManager = ScoreManager()


//-------------------------LIVEDATA---------------------------------------------------------------//


    // LiveData for score

    val userScore: LiveData<Int> get() = scoreManager.userScore

    val computerScore: LiveData<Int> get() = scoreManager.computerScore

    val scoreBoardCollection: LiveData<MutableList<ScoreManager.ScoreBoardRow>> get() = scoreManager.scoreBoardScoreCollection

    // LiveData for user's hand
    val userHand: LiveData<MutableList<Card>> get() = deckManager.userHand

    // LiveData for computer's played card
    val userCard: LiveData<Card> get() = deckManager.userCard

    // LiveData for computer's played card
    val computerCard: LiveData<Card> get() = deckManager.computerCard

    // Livedata for winner
    val winner: LiveData<Player> get() = rulesModule.winner


//------------------------------FUNCTIONS---------------------------------------------------------//


    fun updatePlayerCard(card: Card) {
        deckManager.updatePlayerCard(card)

    }

    fun checkWinner() {
        rulesModule.checkWinner()
    }

    fun resolveTurn() {
        rulesModule.resolveTurn()
    }

    fun randomiseComputerCard(): Card {
        return deckManager.randomiseComputerCard()
    }


}


