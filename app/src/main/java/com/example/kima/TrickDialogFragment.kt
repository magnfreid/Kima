package com.example.kima

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.kima.models.Player

class TrickDialogFragment : DialogFragment() {

    lateinit var vm: GameViewModel

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


        vm.winner.observe(viewLifecycleOwner) { newWinner ->
            tvDisplayTrickWinner.text = "${newWinner.name} won!"
        }

        vm.checkWinner()

        btnNextTrick.setOnClickListener {
            dialog?.dismiss()
        }

    }
}