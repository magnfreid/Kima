package com.example.kima.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class Scoreboard {

    private val _scoreBoardScoreCollection = MutableLiveData<MutableList<ScoreboardRow>>(
        mutableListOf()
    )
    val scoreBoardScoreCollection: LiveData<MutableList<ScoreboardRow>> get() = _scoreBoardScoreCollection

    data class ScoreboardRow(
        val round: Int, val playerScore: Int, val cpuScore: Int
    )

    fun addScoreboardRow(round: Int, playerScore: Int, computerScore: Int) {
        _scoreBoardScoreCollection.value?.let {
            it.add(ScoreboardRow(round, playerScore, computerScore))
            _scoreBoardScoreCollection.value = it
        }
    }
}