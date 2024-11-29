package com.example.kima

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kima.models.Card
import com.example.kima.models.DeckManager

class GameViewModel : ViewModel(), GameLogics {

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

    fun updatePlayerCard(card: Card) {
        deckManager.updatePlayerCard(card)

    }

    override fun checkWinner() {
        rulesModule.checkWinner()
    }

    override fun resolveTurn() {
        rulesModule.resolveTurn()
    }

    /*fun drawFullHand(hand: MutableList<Card>) {
        deck.drawFullHand(hand)
    }*/

}

interface GameLogics {
    fun checkWinner()
    fun resolveTurn()
}