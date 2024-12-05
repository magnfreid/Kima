package com.example.kima.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class Scoreboard {

    private val _scoreboardScoreCollection = MutableLiveData<MutableList<ScoreboardRow>>(
        mutableListOf()
    )
    val scoreboardScoreCollection: LiveData<MutableList<ScoreboardRow>> get() = _scoreboardScoreCollection

    data class ScoreboardRow(
        val round: Int, val playerScore: Int, val cpuScore: Int
    )

    /**
     * Adds a list of scores to the scoreboard collection. To be used when building the scoreboard in the UI.
     */
    fun addScoreboardRow(round: Int, playerScore: Int, computerScore: Int) {
        _scoreboardScoreCollection.value?.let {
            it.add(ScoreboardRow(round, playerScore, computerScore))
            _scoreboardScoreCollection.value = it
        }
    }
}