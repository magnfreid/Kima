package com.example.kima.models

import com.example.kima.R

class Deck {



    fun generateDeck() : MutableList<Card> {
        var deck = mutableListOf<Card>()
//        CLUBS
        deck.add(Card("clubs", 3, R.drawable.clubs_3, "clubs_3"))
        deck.add(Card("clubs", 4, R.drawable.clubs_4, "clubs_4"))
        deck.add(Card("clubs", 5, R.drawable.clubs_5, "clubs_5"))
        deck.add(Card("clubs", 6, R.drawable.clubs_6, "clubs_6"))
        deck.add(Card("clubs", 7, R.drawable.clubs_7, "clubs_7"))
        deck.add(Card("clubs", 8, R.drawable.clubs_8, "clubs_8"))
        deck.add(Card("clubs", 9, R.drawable.clubs_9, "clubs_9"))
        deck.add(Card("clubs", 10, R.drawable.clubs_10, "clubs_10"))
        deck.add(Card("clubs", 11, R.drawable.clubs_jack, "clubs_jack"))
        deck.add(Card("clubs", 12, R.drawable.clubs_queen, "clubs_queen"))
        deck.add(Card("clubs", 13, R.drawable.clubs_king, "clubs_king"))
        deck.add(Card("clubs", 14, R.drawable.clubs_ace, "clubs_ace"))

//        DIAMONDS
        deck.add(Card("diamonds", 3, R.drawable.diamonds_3, "diamonds_3"))
        deck.add(Card("diamonds", 4, R.drawable.diamonds_4, "diamonds_4"))
        deck.add(Card("diamonds", 5, R.drawable.diamonds_5, "diamonds_5"))
        deck.add(Card("diamonds", 6, R.drawable.diamonds_6, "diamonds_6"))
        deck.add(Card("diamonds", 7, R.drawable.diamonds_7, "diamonds_7"))
        deck.add(Card("diamonds", 8, R.drawable.diamonds_8, "diamonds_8"))
        deck.add(Card("diamonds", 9, R.drawable.diamonds_9, "diamonds_9"))
        deck.add(Card("diamonds", 10, R.drawable.diamonds_10, "diamonds_10"))
        deck.add(Card("diamonds", 11, R.drawable.diamonds_jack, "diamonds_jack"))
        deck.add(Card("diamonds", 12, R.drawable.diamonds_queen, "diamonds_queen"))
        deck.add(Card("diamonds", 13, R.drawable.diamonds_king, "diamonds_king"))
        deck.add(Card("diamonds", 14, R.drawable.diamonds_ace, "diamonds_ace"))

//        HEARTS
        deck.add(Card("hearts", 3, R.drawable.hearts_3, "hearts_3"))
        deck.add(Card("hearts", 4, R.drawable.hearts_4, "hearts_4"))
        deck.add(Card("hearts", 5, R.drawable.hearts_5, "hearts_5"))
        deck.add(Card("hearts", 6, R.drawable.hearts_6, "hearts_6"))
        deck.add(Card("hearts", 7, R.drawable.hearts_7, "hearts_7"))
        deck.add(Card("hearts", 8, R.drawable.hearts_8, "hearts_8"))
        deck.add(Card("hearts", 9, R.drawable.hearts_9, "hearts_9"))
        deck.add(Card("hearts", 10, R.drawable.hearts_10, "hearts_10"))
        deck.add(Card("hearts", 11, R.drawable.hearts_jack, "hearts_jack"))
        deck.add(Card("hearts", 12, R.drawable.hearts_queen, "hearts_queen"))
        deck.add(Card("hearts", 13, R.drawable.hearts_king, "hearts_king"))
        deck.add(Card("hearts", 14, R.drawable.hearts_ace, "hearts_ace"))

//        SPADES
        deck.add(Card("spades", 3, R.drawable.spades_3, "spades_3"))
        deck.add(Card("spades", 4, R.drawable.spades_4, "spades_4"))
        deck.add(Card("spades", 5, R.drawable.spades_5, "spades_5"))
        deck.add(Card("spades", 6, R.drawable.spades_6, "spades_6"))
        deck.add(Card("spades", 7, R.drawable.spades_7, "spades_7"))
        deck.add(Card("spades", 8, R.drawable.spades_8, "spades_8"))
        deck.add(Card("spades", 9, R.drawable.spades_9, "spades_9"))
        deck.add(Card("spades", 10, R.drawable.spades_10, "spades_10"))
        deck.add(Card("spades", 11, R.drawable.spades_jack, "spades_jack"))
        deck.add(Card("spades", 12, R.drawable.spades_queen, "spades_queen"))
        deck.add(Card("spades", 13, R.drawable.spades_king, "spades_king"))
        deck.add(Card("spades", 14, R.drawable.ace_spades, "ace_spades"))

        return deck

    }
}