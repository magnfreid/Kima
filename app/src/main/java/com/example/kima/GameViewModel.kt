package com.example.kima

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kima.models.Card
import com.example.kima.models.Deck

class GameViewModel : ViewModel() {

    val deck = Deck()


    // LiveData for score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    // LiveData for user's hand
    val userHand: LiveData<MutableList<Card>> get() = deck.userHand

    // LiveData for computer's played card
    private val _userCard = MutableLiveData<Card>()
    val userCard: LiveData<Card> get() = _userCard

    // LiveData for computer's played card
    private val _computerCard = MutableLiveData<Card>()
    val computerCard: LiveData<Card> get() = _computerCard


    /*fun drawFullHand(hand: MutableList<Card>) {
        deck.drawFullHand(hand)
    }*/

}