package com.example.kima.views

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kima.R
import com.example.kima.viewmodel.GameViewModel
import com.example.kima.databinding.ActivityGameBinding
import com.example.kima.models.Card


class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var vm: GameViewModel
    private val playedCardFragment: Fragment = PlayedCardFragment()

    // Fragment as class variable with call back function.
    private val handOfCardsFragment: HandOfCardsFragment = HandOfCardsFragment {
        showFragment(playedCardFragment)

        if (vm.gameRules.winner == vm.player.value) {
            displayComputerCard(drawReactiveComputerCard())
        }

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
            // Set top are of phone to black.
            v.setBackgroundColor(Color.BLACK)
            insets
        }

        // Deal hands to user and computer.
        vm.player.observe(this) {
            if (it.hand == null) {
                vm.dealPlayerHand()
                vm.dealComputerHand()
            }
        }

        showFragment(handOfCardsFragment)

        setupMenu()

        val tvCpuPoints = binding.tvPointCounterCPU
        val tvUserPoints = binding.tvPointCounterUser
        val pointString = getString(R.string.points_text)

        // Observe points and update text views.
        vm.computer.observe(this) { score ->
            val textString = "${score.score} $pointString"
            tvCpuPoints.text = textString
        }
        vm.player.observe(this) { score ->
            val textString = "${score.score} $pointString"
            tvUserPoints.text = textString
        }

    }

// ----------------------------FUNCTIONS------------------------------------------------------------
    fun revertToComputerCardHolder() {
        val ivCardholder = binding.presentComputerCard
        ivCardholder.setImageDrawable(null)
    }

    private fun setupMenu() {
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
    }


    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fcvPlayer.id, fragment)
            commit()
        }
    }

    fun displayComputerCard(card: Card) {
        binding.presentComputerCard.setImageResource(card.id)
    }

    private fun drawReactiveComputerCard(): Card {
        val card = vm.computerReactiveCardPick()
        return card
    }

    fun showHandOfCards() {
        showFragment(handOfCardsFragment)
    }

    fun showScoreboard() {
        ScoreboardDialogFragment().show(supportFragmentManager, "Scoreboard")
    }
}