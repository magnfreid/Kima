package com.example.kima.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kima.R

class Deck {

    val deck: MutableList<Card> = generateDeck()

    private val _userHand = MutableLiveData<MutableList<Card>>()
    val userHand: LiveData<MutableList<Card>> get() = _userHand

    private val _computerHand = MutableLiveData<MutableList<Card>>()
    val computerHand: LiveData<MutableList<Card>> get() = _computerHand


    init {
        _userHand.value = drawFullHand(userHand.value ?: mutableListOf())
        _computerHand.value = drawFullHand(computerHand.value ?: mutableListOf())
    }




    private fun randomiseCard(deck : MutableList<Card>) : Int {
        return (0 until deck.size).random()
    }

    private fun drawCardUser(deck: MutableList<Card>, hand: MutableList<Card>) {
        val randomIndex = randomiseCard(deck)
        val randomCard = deck[randomIndex]
        deck.removeAt(randomIndex)
        hand.add(randomCard)
    }

    fun drawFullHand(hand: MutableList<Card>) : MutableList<Card> {
        hand.clear()

        for(i in 1..5) {
            drawCardUser(deck, hand)
        }

        return hand
    }




    fun generateDeck() : MutableList<Card> {
        var deck = mutableListOf<Card>()
            //        CLUBS
        deck.add(Card("clubs", 3, R.drawable.clubs_3))
        deck.add(Card("clubs", 4, R.drawable.clubs_4))
        deck.add(Card("clubs", 5, R.drawable.clubs_5))
        deck.add(Card("clubs", 6, R.drawable.clubs_6))
        deck.add(Card("clubs", 7, R.drawable.clubs_7))
        deck.add(Card("clubs", 8, R.drawable.clubs_8))
        deck.add(Card("clubs", 9, R.drawable.clubs_9))
        deck.add(Card("clubs", 10, R.drawable.clubs_10))
        deck.add(Card("clubs", 11, R.drawable.clubs_jack))
        deck.add(Card("clubs", 12, R.drawable.clubs_queen))
        deck.add(Card("clubs", 13, R.drawable.clubs_king))
        deck.add(Card("clubs", 14, R.drawable.clubs_ace))

//        DIAMONDS
        deck.add(Card("diamonds", 3, R.drawable.diamonds_3))
        deck.add(Card("diamonds", 4, R.drawable.diamonds_4))
        deck.add(Card("diamonds", 5, R.drawable.diamonds_5))
        deck.add(Card("diamonds", 6, R.drawable.diamonds_6))
        deck.add(Card("diamonds", 7, R.drawable.diamonds_7))
        deck.add(Card("diamonds", 8, R.drawable.diamonds_8))
        deck.add(Card("diamonds", 9, R.drawable.diamonds_9))
        deck.add(Card("diamonds", 10, R.drawable.diamonds_10))
        deck.add(Card("diamonds", 11, R.drawable.diamonds_jack))
        deck.add(Card("diamonds", 12, R.drawable.diamonds_queen))
        deck.add(Card("diamonds", 13, R.drawable.diamonds_king))
        deck.add(Card("diamonds", 14, R.drawable.diamonds_ace))

//        HEARTS
        deck.add(Card("hearts", 3, R.drawable.hearts_3))
        deck.add(Card("hearts", 4, R.drawable.hearts_4))
        deck.add(Card("hearts", 5, R.drawable.hearts_5))
        deck.add(Card("hearts", 6, R.drawable.hearts_6))
        deck.add(Card("hearts", 7, R.drawable.hearts_7))
        deck.add(Card("hearts", 8, R.drawable.hearts_8))
        deck.add(Card("hearts", 9, R.drawable.hearts_9))
        deck.add(Card("hearts", 10, R.drawable.hearts_10))
        deck.add(Card("hearts", 11, R.drawable.hearts_jack))
        deck.add(Card("hearts", 12, R.drawable.hearts_queen))
        deck.add(Card("hearts", 13, R.drawable.hearts_king))
        deck.add(Card("hearts", 14, R.drawable.hearts_ace))

//        SPADES
        deck.add(Card("spades", 3, R.drawable.spades_3))
        deck.add(Card("spades", 4, R.drawable.spades_4))
        deck.add(Card("spades", 5, R.drawable.spades_5))
        deck.add(Card("spades", 6, R.drawable.spades_6))
        deck.add(Card("spades", 7, R.drawable.spades_7))
        deck.add(Card("spades", 8, R.drawable.spades_8))
        deck.add(Card("spades", 9, R.drawable.spades_9))
        deck.add(Card("spades", 10, R.drawable.spades_10))
        deck.add(Card("spades", 11, R.drawable.spades_jack))
        deck.add(Card("spades", 12, R.drawable.spades_queen))
        deck.add(Card("spades", 13, R.drawable.spades_king))
        deck.add(Card("spades", 14, R.drawable.ace_spades))


        return deck

    }
}