package com.example.kima

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kima.databinding.ActivityGameBinding
import com.example.kima.models.Card
import com.example.kima.models.DeckManager
import com.example.kima.views.ScoreBoardDialogFragment

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    lateinit var vm : GameViewModel

    val playedCardFragment: Fragment = PlayedCardFragment()
    val handOfCardsFragment: HandOfCardsFragment = HandOfCardsFragment {
        //TODO visa även motspelarens kort här

        showFragment(playedCardFragment)

        val computerCard: Card?
        computerCard = displayComputerCard()
        binding.presentComputerCard.setImageResource(computerCard.id)

        val trickDialogFragment = TrickDialogFragment()
        trickDialogFragment.show(supportFragmentManager, "Trick")


        Log.d("SOUT", "${vm.userCard.value}")
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        binding = ActivityGameBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this).get(GameViewModel::class.java)

        val menuItems = listOf("Rules", "Exit")
        val menuAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, menuItems)
        val autoCompleteTextView = binding.menu.editText as? AutoCompleteTextView
        autoCompleteTextView?.setAdapter(menuAdapter)

        autoCompleteTextView?.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = menuItems[position]
            when (selectedItem) {
                "Rules" -> {
                    // Visa reglerna
                    val dialogFragment = RulesDialogFragment()
                    dialogFragment.show(supportFragmentManager, "Test")
                }
                "Exit" -> {
                    // Avsluta spelet
                    finish()

                }
            }
        }

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showFragment(handOfCardsFragment)

        //TODO Placeholder for testing the scoreboard fragment, move to the correct place when possible
        binding.btnShowHand.setOnClickListener {
            ScoreBoardDialogFragment().show(supportFragmentManager, "Scoreboard")
        }


        vm.computerCard.observe(this) {
            if(it != null) {
//                binding.presentComputerCard.setImageResource(computerCard!!.id)
            }
        }
    }



    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fcvPlayer.id, fragment)
            commit()
        }
    }

    private fun displayComputerCard() : Card {
        val card = vm.randomiseComputerCard()
        return card

    }
}