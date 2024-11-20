package com.example.kima

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kima.controllers.HandAdapter
import com.example.kima.databinding.FragmentHandOfCardsBinding
import com.example.kima.models.Card

/**
 * A simple [Fragment] subclass.
 * Use the [HandOfCardsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HandOfCardsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hand_of_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_hand)
        recyclerView.adapter = HandAdapter(testList)
      recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    private val testList = mutableListOf(
        Card("diamonds", 3, R.drawable.diamonds_3, "diamonds_3"),
        (Card("diamonds", 4, R.drawable.diamonds_4, "diamonds_4")),
        (Card("diamonds", 5, R.drawable.diamonds_5, "diamonds_5")),
        (Card("diamonds", 6, R.drawable.diamonds_6, "diamonds_6")),
        (Card("diamonds", 7, R.drawable.diamonds_7, "diamonds_7"))
    )

}