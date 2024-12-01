package com.example.kima.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kima.viewmodel.GameViewModel
import com.example.kima.R
import com.example.kima.databinding.ActivityGameBinding
import com.example.kima.models.Card

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    lateinit var vm: GameViewModel
    val playedCardFragment: Fragment = PlayedCardFragment()
    val handOfCardsFragment: HandOfCardsFragment = HandOfCardsFragment {
        showFragment(playedCardFragment)
        val computerCard: Card?
        computerCard = displayComputerCard()
        binding.presentComputerCard.setImageResource(computerCard.id)
        val trickDialogFragment = TrickDialogFragment()
        trickDialogFragment.show(supportFragmentManager, "Trick")
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this)[GameViewModel::class.java]

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        vm.dealPlayerHand()
        vm.dealComputerHand()

        showFragment(handOfCardsFragment)

        //TODO Placeholder for testing the scoreboard fragment, move to the correct place when possible
        binding.btnShowHand.setOnClickListener {
            ScoreboardDialogFragment().show(supportFragmentManager, "Scoreboard")
        }
    }


    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fcvPlayer.id, fragment)
            commit()
        }
    }

    private fun displayComputerCard(): Card {
        val card = vm.randomiseComputerCard()
        return card

    }
}