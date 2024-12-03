package com.example.kima.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kima.R
import com.example.kima.viewmodel.GameViewModel

class TrickDialogFragment : DialogFragment() {

    private lateinit var vm: GameViewModel
    val playedCardFragment: Fragment = PlayedCardFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_tricks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNextTrick = view.findViewById<Button>(R.id.btn_next_trick)
        val tvDisplayTrickWinner = view.findViewById<TextView>(R.id.tv_display_trick_winner)

        val winner = vm.checkWinner()
        val winnerString = "${winner?.name} won!"
        tvDisplayTrickWinner.text = winnerString
        vm.resolveTurn()

        btnNextTrick.setOnClickListener {

            vm.resetTrick()

            (activity as? GameActivity)?.showHandOfCards()

            vm.imageChangeEvent.value = true

            dialog?.dismiss()


        }

    }
}