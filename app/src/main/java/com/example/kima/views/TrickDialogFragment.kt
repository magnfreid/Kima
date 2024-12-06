package com.example.kima.views

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.kima.R
import com.example.kima.viewmodel.GameViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

/**
 * A small popup that shows after each round is resolved. Shows the winner of the round and a continue button, or a
 * button to show the scoreboard when the game is over.
 */
class TrickDialogFragment : DialogFragment() {

    private lateinit var vm: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProvider(requireActivity())[GameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_tricks, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setDimAmount(0.0f) // Remove blurred window behind dialog fragment.
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNextTrick = view.findViewById<MaterialButton>(R.id.btn_next_trick)
        val tvDisplayTrickWinner = view.findViewById<MaterialTextView>(R.id.tv_display_trick_winner)

//----------------------- Check who wins the trick round. ----------------------------------------
        // Check winner and set text.
        val winner = vm.checkWinner()
        val winnerString = if (winner == vm.player.value) {
            getString(R.string.btn_you_won_tricks_dialog)
        } else {
            getString(R.string.btn_computer_won_tricks_dialog)
        }
        tvDisplayTrickWinner.text = winnerString
        // Give points.
        vm.resolveTurn()

        // Check how many tricks have been played.
        vm.trickCounter.observe(viewLifecycleOwner) {
            if (it < 4) { // All tricks but the last.
                btnNextTrick.setOnClickListener {
                    val gameActivity = (activity as? GameActivity)
                    if (vm.gameRules.winner == vm.computer.value) {
                        gameActivity?.displayComputerCard(vm.randomiseComputerCard())
                    } else {
                        gameActivity?.revertToComputerCardHolder()

                    }
                    vm.incrementTricks()
                    (activity as? GameActivity)?.showHandOfCards()
                    vm.imageChangeEvent.value = true
                    dialog?.dismiss()
                }
            } else { // The last trick.
                tvDisplayTrickWinner.text = winnerString
                val btnText = getString(R.string.scoreboard_show)
                btnNextTrick.text = btnText
                btnNextTrick.setOnClickListener {
                    (activity as? GameActivity)?.showScoreboard()
                }
            }
        }
    }
}