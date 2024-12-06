package com.example.kima.models


import com.example.kima.R

class DeckManager {
    private val deck: MutableList<Card> = generateDeck()

    companion object{
        const val HEARTS = "hearts"
        const val CLUBS = "clubs"
        const val DIAMONDS = "diamonds"
        const val SPADES = "spades"
    }

    private fun drawCard(): Card {
        val randomIndex = (0 until deck.size).random()
        val randomCard = deck[randomIndex]
        deck.removeAt(randomIndex)
        return randomCard
    }

    fun drawFullHand(): MutableList<Card> {
        val hand = mutableListOf<Card>()
        repeat(5) {
            hand.add(drawCard())
        }
        return hand
    }


    private fun generateDeck(): MutableList<Card> {
        val deck = mutableListOf<Card>()
        //        CLUBS
        deck.add(Card(CLUBS, 2, R.drawable.clubs_2))
        deck.add(Card(CLUBS, 3, R.drawable.clubs_3))
        deck.add(Card(CLUBS, 4, R.drawable.clubs_4))
        deck.add(Card(CLUBS, 5, R.drawable.clubs_5))
        deck.add(Card(CLUBS, 6, R.drawable.clubs_6))
        deck.add(Card(CLUBS, 7, R.drawable.clubs_7))
        deck.add(Card(CLUBS, 8, R.drawable.clubs_8))
        deck.add(Card(CLUBS, 9, R.drawable.clubs_9))
        deck.add(Card(CLUBS, 10, R.drawable.clubs_10))
        deck.add(Card(CLUBS, 11, R.drawable.clubs_jack))
        deck.add(Card(CLUBS, 12, R.drawable.clubs_queen))
        deck.add(Card(CLUBS, 13, R.drawable.clubs_king))
        deck.add(Card(CLUBS, 14, R.drawable.clubs_ace))

//        DIAMONDS
        deck.add(Card(DIAMONDS, 2, R.drawable.diamonds_2))
        deck.add(Card(DIAMONDS, 3, R.drawable.diamonds_3))
        deck.add(Card(DIAMONDS, 4, R.drawable.diamonds_4))
        deck.add(Card(DIAMONDS, 5, R.drawable.diamonds_5))
        deck.add(Card(DIAMONDS, 6, R.drawable.diamonds_6))
        deck.add(Card(DIAMONDS, 7, R.drawable.diamonds_7))
        deck.add(Card(DIAMONDS, 8, R.drawable.diamonds_8))
        deck.add(Card(DIAMONDS, 9, R.drawable.diamonds_9))
        deck.add(Card(DIAMONDS, 10, R.drawable.diamonds_10))
        deck.add(Card(DIAMONDS, 11, R.drawable.diamonds_jack))
        deck.add(Card(DIAMONDS, 12, R.drawable.diamonds_queen))
        deck.add(Card(DIAMONDS, 13, R.drawable.diamonds_king))
        deck.add(Card(DIAMONDS, 14, R.drawable.diamonds_ace))

//        HEARTS
        deck.add(Card(HEARTS, 2, R.drawable.hearts_2))
        deck.add(Card(HEARTS, 3, R.drawable.hearts_3))
        deck.add(Card(HEARTS, 4, R.drawable.hearts_4))
        deck.add(Card(HEARTS, 5, R.drawable.hearts_5))
        deck.add(Card(HEARTS, 6, R.drawable.hearts_6))
        deck.add(Card(HEARTS, 7, R.drawable.hearts_7))
        deck.add(Card(HEARTS, 8, R.drawable.hearts_8))
        deck.add(Card(HEARTS, 9, R.drawable.hearts_9))
        deck.add(Card(HEARTS, 10, R.drawable.hearts_10))
        deck.add(Card(HEARTS, 11, R.drawable.hearts_jack))
        deck.add(Card(HEARTS, 12, R.drawable.hearts_queen))
        deck.add(Card(HEARTS, 13, R.drawable.hearts_king))
        deck.add(Card(HEARTS, 14, R.drawable.hearts_ace))

//        SPADES
        deck.add(Card(SPADES, 2, R.drawable.spades_2))
        deck.add(Card(SPADES, 3, R.drawable.spades_3))
        deck.add(Card(SPADES, 4, R.drawable.spades_4))
        deck.add(Card(SPADES, 5, R.drawable.spades_5))
        deck.add(Card(SPADES, 6, R.drawable.spades_6))
        deck.add(Card(SPADES, 7, R.drawable.spades_7))
        deck.add(Card(SPADES, 8, R.drawable.spades_8))
        deck.add(Card(SPADES, 9, R.drawable.spades_9))
        deck.add(Card(SPADES, 10, R.drawable.spades_10))
        deck.add(Card(SPADES, 11, R.drawable.spades_jack))
        deck.add(Card(SPADES, 12, R.drawable.spades_queen))
        deck.add(Card(SPADES, 13, R.drawable.spades_king))
        deck.add(Card(SPADES, 14, R.drawable.ace_spades))
        return deck
    }
}