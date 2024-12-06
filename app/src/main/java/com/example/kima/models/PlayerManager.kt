package com.example.kima.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PlayerManager {

    private val _player = MutableLiveData(Player("You"))
    val player: LiveData<Player> get() = _player
    private val _computer = MutableLiveData(Player("Computer"))
    val computer: LiveData<Player> get() = _computer


//------------------------------FUNCTIONS---------------------------------------------------------//

    fun updatePlayerHand(newHand: MutableList<Card>) {
        _player.value?.let {
            it.hand = newHand
            _player.value = it
        }
    }

    fun updateComputerHand(newHand: MutableList<Card>) {
        _computer.value?.let {
            it.hand = newHand
            _computer.value = it
        }
    }

    fun updatePlayerPlayedCard(card: Card) {
        _player.value?.let {
            it.playedCard = card
            _player.value = it
        }
    }

    fun removePlayerCardFromHand(card: Card) {
        _player.value?.let {
            it.hand?.remove(card)
            _player.value = it
        }
    }

    fun incrementPlayerScore() {
        _player.value?.let {
            it.score += 1
            _player.value = it
        }
    }

    fun incrementComputerScore() {
        _computer.value?.let {
            it.score += 1
            _computer.value = it
        }
    }

    fun drawComputerRandomCard(): Card {
        val currentComputerHand = _computer.value?.hand ?: mutableListOf()
        val randomIndex = (0 until currentComputerHand.size).random()
        val randomCard = currentComputerHand[randomIndex]
        currentComputerHand.removeAt(randomIndex)
        _computer.value?.hand = currentComputerHand
        _computer.value?.playedCard = randomCard
        return randomCard
    }

    /**
     * Logic for when computer reacts to the player's placed card. Ensures following suit when possible
     * and places the card with the lowest rank if the suit in play is missing from computer's hand.
     * @return Returns the best card from the computer's hand.
     */
    fun drawComputerReactiveCard(): Card {
        val computerHand = _computer.value?.hand ?: mutableListOf()
        val tempHand = mutableListOf<Card>()
        var pickedCard: Card
        for (card in computerHand) {
            if (card.suit == _player.value?.playedCard?.suit) {
                tempHand.add(card)
            }
        }
        tempHand.sortBy { it.rank }
        if (tempHand.isNotEmpty()) {
            pickedCard = tempHand[0]
            for (card in tempHand) {
                if (card.rank > _player.value?.playedCard?.rank!!) {
                    pickedCard = card
                    break
                }
            }
        } else {
            computerHand.sortBy { it.rank }
            pickedCard = computerHand[0]

        }
        computerHand.remove(pickedCard)
        _computer.value?.playedCard = pickedCard
        return pickedCard
    }

    /**
     * Ensures that the player has to follow suit when possible when trying to place a card.
     * @return Returns true if the selected card is viable for play.
     */
    fun checkCardPlacementViability(card: Card): Boolean {
        val computerPlayedCard = _computer.value?.playedCard
        val playerHand = _player.value?.hand ?: mutableListOf()
        var viable = true
        for (cardOnHand in playerHand) {
            if (cardOnHand.suit == computerPlayedCard?.suit) {
                viable = false
                break
            }
        }
        return if (!viable) {
            card.suit == computerPlayedCard?.suit
        } else true
    }

}