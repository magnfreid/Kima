package com.example.kima

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.kima.databinding.FragmentHandOfCardsBinding
import com.example.kima.models.Card

/**
 * A simple [Fragment] subclass.
 * Use the [HandOfCardsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HandOfCardsFragment(
    val onShowPlayedHand: () -> Unit
) : Fragment() {
    val userHandView: MutableList<ImageView> = mutableListOf()
    lateinit var binding: FragmentHandOfCardsBinding
    lateinit var vm: GameViewModel
    var userHand = mutableListOf<Card>()
    var chosenCard: Card? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHandOfCardsBinding.inflate(inflater, container, false)

        vm = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.userHand.observe(viewLifecycleOwner) {
            userHand = it
            setUpImageViewsForUserHand(view)
            displayUserHand(userHandView, userHand)
        }

        binding.btnPlaceCard.setOnClickListener {
            Log.d("SOUT", "${vm.userCard.value} BEFORE")

            for (card in userHand) {
                    if (card.isRaised) {
                        chosenCard = card
                        break
                    }
                }
            if(chosenCard != null) {
                vm.updatePlayerCard(chosenCard!!)
                Log.d("SOUT", "${vm.userCard.value} AFTER")
                Log.d("SOUT", "$vm HOC FRAGMENT")
                onShowPlayedHand()
            }
        }
    }

    private fun setUpImageViewsForUserHand(view: View) {
        val ivUserCard1 = view.findViewById<ImageView>(R.id.iv_user_card_1)
        val ivUserCard2 = view.findViewById<ImageView>(R.id.iv_user_card_2)
        val ivUserCard3 = view.findViewById<ImageView>(R.id.iv_user_card_3)
        val ivUserCard4 = view.findViewById<ImageView>(R.id.iv_user_card_4)
        val ivUserCard5 = view.findViewById<ImageView>(R.id.iv_user_card_5)

        userHandView.add(ivUserCard1)
        userHandView.add(ivUserCard2)
        userHandView.add(ivUserCard3)
        userHandView.add(ivUserCard4)
        userHandView.add(ivUserCard5)


        for (imageView in userHandView) {
            imageView.setOnClickListener {
                if (userHand[userHandView.indexOf(imageView)].isRaised) {
                    imageView.animate().translationY(0f).setDuration(150)
                    userHand[userHandView.indexOf(imageView)].isRaised = false
                } else {
                    imageView.animate().translationY(-50f).setDuration(150)
                    userHand[userHandView.indexOf(imageView)].isRaised = true
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