package com.example.kima

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kima.databinding.ActivityGameBinding
import com.example.kima.models.Card
import com.example.kima.models.Deck

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    val deck: MutableList<Card> = Deck().generateDeck()

    lateinit var ivPresentComputerCard: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivPresentComputerCard = findViewById(R.id.present_computer_card)

        val randomIndex = randomiseCard()
        val randomCard = deck[randomIndex]
        val nameFromCard = randomCard.imageName
        val idFromCard = resources.getIdentifier(nameFromCard, "drawable", packageName)
        ivPresentComputerCard.setImageResource(idFromCard)
        showHandFragment()

    }

    fun randomiseCard(): Int {
        return (0..47).random()
    }

    private fun showHandFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fcvPlayer.id, HandOfCardsFragment())
            commit()
        }
    }
}