package com.example.kima.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PlayerManager {

    private val _player = MutableLiveData(Player("You"))
    val player: LiveData<Player> get() = _player
    private val _computer = MutableLiveData(Player("Computer"))
    val computer: LiveData<Player> get() = _computer

    data class Player(val name: String) {
        var hand: MutableList<Card>? = null
        var playedCard: Card? = null
        var score: Int = 0
    }

    fun drawPlayerHand(newHand: MutableList<Card>) {
        _player.value?.let {
            it.hand = newHand
            _player.value = it
        }
    }

    fun drawComputerHand(newHand: MutableList<Card>) {
        _computer.value?.let {
            it.hand = newHand
            _computer.value = it
        }
    }

    fun incrementPlayerScore() {
        _player.value?.let {
            it.score += 1
            _player.value = it
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
    }
}