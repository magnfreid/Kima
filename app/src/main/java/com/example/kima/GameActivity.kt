package com.example.kima

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kima.databinding.ActivityGameBinding
import com.example.kima.models.Card
import com.example.kima.models.DeckManager

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    lateinit var vm : GameViewModel
val playedCardFragment: Fragment = PlayedCardFragment()
    val handOfCardsFragment: HandOfCardsFragment = HandOfCardsFragment {
        showFragment(playedCardFragment)
    }

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



        showFragment(handOfCardsFragment)

        /*ivPresentComputerCard = findViewById(R.id.present_computer_card)

        val randomIndex = randomiseCard(deck)
        val randomCard = deck[randomIndex]
        val nameFromCard = randomCard.imageName
        val idFromCard = resources.getIdentifier(nameFromCard, "drawable", packageName)
        ivPresentComputerCard.setImageResource(idFromCard)
        showHandFragment()*/


    }



    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fcvPlayer.id, fragment)
            commit()
        }
    }
}