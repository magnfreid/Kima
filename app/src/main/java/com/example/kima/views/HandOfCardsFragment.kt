package com.example.kima.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.kima.R
import com.example.kima.databinding.FragmentHandOfCardsBinding
import com.example.kima.models.Card
import com.example.kima.viewmodel.GameViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HandOfCardsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HandOfCardsFragment(
    val onShowPlayedHand: () -> Unit
) : Fragment() {
    private val userHandView: MutableList<ImageView> = mutableListOf()
    private lateinit var binding: FragmentHandOfCardsBinding
    private lateinit var vm: GameViewModel
    var userHand = mutableListOf<Card>()
    var chosenCard: Card? = null

    lateinit var ivUserCard1: ImageView
    lateinit var ivUserCard2: ImageView
    lateinit var ivUserCard3: ImageView
    lateinit var ivUserCard4: ImageView
    lateinit var ivUserCard5: ImageView


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



        vm.player.observe(viewLifecycleOwner) {

            userHand = it.hand ?: mutableListOf()
            setUpImageViewsForUserHand(view)
            displayUserHand(userHandView, userHand)

        }

        vm.startNextTrick.observe(viewLifecycleOwner) {
            startNextTrick ->
            if(startNextTrick) {
                vm.player.observe(viewLifecycleOwner) {
                    userHand = it.hand ?: mutableListOf()
                    setUpImageViewsForUserHand(view)
                    displayUserHand(userHandView, userHand)
                }

                binding.btnPlaceCard.setOnClickListener {
                    for (card in userHand) {
                        if (card.isRaised) {
                            chosenCard = card
                            break
                        }
                    }
                    if(chosenCard != null) {
                        vm.updatePlayerCard(chosenCard!!)
                        parentLayoutForHand.removeView(userHandView[userHand.indexOf(chosenCard)])
//                        userHandView.remove(userHandView[userHand.indexOf(chosenCard)])
//                        userHand.remove(chosenCard)
                        vm.removePlayedCard(chosenCard!!)
                        onShowPlayedHand()
                    }

                }


//                vm.startNextTrickConsumed()
            }
        }


    }

    private fun setUpImageViewsForUserHand(view: View) {
        Log.i("!!!", "testing to display hand")
//            Log.i("???", it.hand.toString())
        /*val ivUserCard1 = view.findViewById<ImageView>(R.id.iv_user_card_1)
        val ivUserCard2 = view.findViewById<ImageView>(R.id.iv_user_card_2)
        val ivUserCard3 = view.findViewById<ImageView>(R.id.iv_user_card_3)
        val ivUserCard4 = view.findViewById<ImageView>(R.id.iv_user_card_4)
        val ivUserCard5 = view.findViewById<ImageView>(R.id.iv_user_card_5)*/

        userHandView.add(ivUserCard1)
        userHandView.add(ivUserCard2)
        userHandView.add(ivUserCard3)
        userHandView.add(ivUserCard4)
        userHandView.add(ivUserCard5)

        var raisedCardIndex = -1


        for (imageView in userHandView) {
            imageView.setOnClickListener {
                val currentIndex = userHandView.indexOf(imageView)
                if (userHand[currentIndex].isRaised) {
                    imageView.animate().translationY(0f).setDuration(150)
                    userHand[currentIndex].isRaised = false
                    raisedCardIndex = -1
                } else {
                    if(raisedCardIndex != -1) {
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


    fun displayUserHand(userHandView: MutableList<ImageView>, userHand: MutableList<Card>) {
        for (i in 0..userHand.size - 1) {
            val card = userHand[i]
            userHandView[i].setImageResource(card.id)

        }

    }
}