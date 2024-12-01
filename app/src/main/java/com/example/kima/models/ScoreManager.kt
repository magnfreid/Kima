package com.example.kima.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ScoreManager {



    private val _scoreBoardScoreCollection = MutableLiveData<MutableList<ScoreBoardRow>>(
        mutableListOf()
    )
    val scoreBoardScoreCollection: LiveData<MutableList<ScoreBoardRow>> get() = _scoreBoardScoreCollection

    private val _userScore = MutableLiveData<Int>()
    val userScore: LiveData<Int> get() = _userScore

    private val _computerScore = MutableLiveData<Int>()
    val computerScore: LiveData<Int> get() = _computerScore

    fun addPlayerScore() {
        _userScore.value = _userScore.value?.plus(1)
    }

    fun addComputerScore() {
        _computerScore.value = _computerScore.value?.plus(1)
    }

    fun resetScores() {
        _userScore.value = 0
        _computerScore.value = 0
    }


    //TODO for testing, remove when live
    init {
        val scores = mutableListOf<ScoreBoardRow>()
        scores.add(ScoreBoardRow(1, 4, 6))
        scores.add(ScoreBoardRow(2, 3, 5))
        scores.add(ScoreBoardRow(3, 2, 4))
        _scoreBoardScoreCollection.value =scores
        _userScore.value = 57
        _computerScore.value =13
    }

    data class ScoreBoardRow(
        val round: Int = 1,
        val playerScore: Int,
        val cpuScore: Int
    )


}