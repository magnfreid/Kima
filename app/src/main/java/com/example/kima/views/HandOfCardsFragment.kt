package com.example.kima.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kima.R
import com.example.kima.databinding.FragmentHandOfCardsBinding
import com.example.kima.models.Card
import com.example.kima.viewmodel.GameViewModel

/**
 * Displays the player's hand of cards and a play card button.
 */
class HandOfCardsFragment(
    val onShowPlayedHand: () -> Unit
) : Fragment() {
    private val userHandView: MutableList<ImageView> = mutableListOf()
    private lateinit var binding: FragmentHandOfCardsBinding
    private lateinit var vm: GameViewModel
    private var userHand = mutableListOf<Card>()
    private var chosenCard: Card? = null

    private lateinit var ivUserCard1: ImageView
    private lateinit var ivUserCard2: ImageView
    private lateinit var ivUserCard3: ImageView
    private lateinit var ivUserCard4: ImageView
    private lateinit var ivUserCard5: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHandOfCardsBinding.inflate(inflater, container, false)

        vm = ViewModelProvider(requireActivity())[GameViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentLayoutForHand = binding.layoutForHand
        ivUserCard1 = view.findViewById(R.id.iv_user_card_1)
        ivUserCard2 = view.findViewById(R.id.iv_user_card_2)
        ivUserCard3 = view.findViewById(R.id.iv_user_card_3)
        ivUserCard4 = view.findViewById(R.id.iv_user_card_4)
        ivUserCard5 = view.findViewById(R.id.iv_user_card_5)

        vm.startNextTrick.observe(viewLifecycleOwner) { startNextTrick ->
            if (startNextTrick) {
                vm.startNextTrickConsumed()
                Log.i("???", userHand.toString())
                userHandView.clear() // Clear the list before adding ImageViews
                Log.i("!!!", userHand.toString())

                vm.player.observe(viewLifecycleOwner) {
                    userHand = it.hand ?: mutableListOf()
                    setUpImageViewsForUserHand()
                    displayUserHand(userHandView, userHand)
                    Log.i("AAA", userHand.toString())
                }

                binding.btnPlaceCard.setOnClickListener {
                    for (card in userHand) {
                        if (card.isRaised) {
                            chosenCard = card
                            break
                        }
                    }
                    if (chosenCard != null) {
                        var viableCardPlacement = true
                        vm.computer.observe(viewLifecycleOwner) {
                            if (vm.gameRules.winner == it) {
                                Log.d("SOUT", "Computer is winner observed.")
                                viableCardPlacement = vm.checkCardPlacementViability(chosenCard!!)
                                Log.d("SOUT", "Viability set to: $viableCardPlacement")
                            }
                        }
                        if (viableCardPlacement) {
                            vm.updatePlayerCard(chosenCard!!)
                            parentLayoutForHand.removeView(userHandView[userHand.indexOf(chosenCard)])
                            vm.removePlayedCard(chosenCard!!)
                            onShowPlayedHand()
                            Log.i("BBB", userHand.toString())
                        } else {
                            Toast.makeText(
                                context,
                                "You need to follow the suit when possible!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }
        }


    }

    private fun setUpImageViewsForUserHand() {
        userHandView.add(ivUserCard1)
        userHandView.add(ivUserCard2)
        userHandView.add(ivUserCard3)
        userHandView.add(ivUserCard4)
        userHandView.add(ivUserCard5)

        var raisedCardIndex = -1

        for (i in 0 until userHand.size) {
            val imageView = userHandView[i]
            imageView.setOnClickListener {
                val currentIndex = userHandView.indexOf(imageView)
                if (userHand[currentIndex].isRaised) {
                    imageView.animate().translationY(0f).setDuration(150)
                    userHand[currentIndex].isRaised = false
                    raisedCardIndex = -1
                } else {
                    if (raisedCardIndex != -1) {
                        userHandView[raisedCardIndex].animate().translationY(0f).setDuration(150)
                        userHand[raisedCardIndex].isRaised = false

                    }
                    imageView.animate().translationY(-50f).setDuration(150)
                    userHand[currentIndex].isRaised = true
                    raisedCardIndex = currentIndex
                }
            }
        }

    }

    private fun displayUserHand(userHandView: MutableList<ImageView>, userHand: MutableList<Card>) {
        for (i in 0..<userHand.size) {
            val card = userHand[i]
            userHandView[i].setImageResource(card.id)

        }

    }
}