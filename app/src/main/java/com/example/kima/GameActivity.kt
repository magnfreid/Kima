package com.example.kima

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.kima.databinding.ActivityGameBinding
import com.example.kima.models.Card
import com.example.kima.models.Deck

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    val deck: MutableList<Card> = Deck().generateDeck()
    val userHand : MutableList<Card> = mutableListOf()
    val computerHand : MutableList<Card> = mutableListOf()
    lateinit var vm : GameViewModel

//    lateinit var ivPresentComputerCard: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {

//        drawFullHand(userHand)
//        drawFullHand(computerHand)

        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this).get(GameViewModel::class.java)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        showHandFragment()

        /*ivPresentComputerCard = findViewById(R.id.present_computer_card)

        val randomIndex = randomiseCard(deck)
        val randomCard = deck[randomIndex]
        val nameFromCard = randomCard.imageName
        val idFromCard = resources.getIdentifier(nameFromCard, "drawable", packageName)
        ivPresentComputerCard.setImageResource(idFromCard)
        showHandFragment()*/


    }



    private fun showHandFragment() {
        val fragment = HandOfCardsFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fcvPlayer.id, fragment)
            commit()
        }
    }
}