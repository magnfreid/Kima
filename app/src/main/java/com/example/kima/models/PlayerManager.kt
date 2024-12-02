package com.example.kima.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PlayerManager {

    private val _player = MutableLiveData(Player("You"))
    val player: LiveData<Player> get() = _player
    private val _computer = MutableLiveData(Player("Computer"))
    val computer: LiveData<Player> get() = _computer

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

    fun updateUserPlayedCard(card: Card){
        _player.value?.let {
            it.playedCard = card
            _player.value = it
        }
    }

    fun removePlayedCard(card : Card) {
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

    fun resetScores() {
        _player.value?.let {
            it.score = 0
            _player.value = it
        }
        _computer.value?.let {
            it.score = 0
            _computer.value = it
        }

    }

    fun resetPlayedCards() {
        _player.value?.playedCard = null
        _computer.value?.playedCard = null
        _player.value = _player.value //Trigger LiveData update
        _computer.value = _computer.value // Trigger LiveData update
    }


    fun randomiseComputerCard(): Card {
        val currentComputerHand = _computer.value?.hand ?: mutableListOf()
        val randomIndex = (1 until currentComputerHand.size).random()
        val randomCard = currentComputerHand[randomIndex]
        currentComputerHand.removeAt(randomIndex)
        _computer.value?.hand = currentComputerHand
        _computer.value?.playedCard = randomCard
        return randomCard
    }

}