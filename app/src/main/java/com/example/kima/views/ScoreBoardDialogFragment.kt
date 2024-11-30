package com.example.kima.views

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.kima.GameViewModel
import com.example.kima.databinding.DialogScoreboardBinding
import com.example.kima.models.ScoreManager
import com.google.android.material.textview.MaterialTextView

class ScoreBoardDialogFragment : DialogFragment() {
    private lateinit var binding: DialogScoreboardBinding
    private lateinit var vm: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = DialogScoreboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(requireActivity())[GameViewModel::class.java]
        val grid = binding.gridScoreboard
        var row = 1
        vm.scoreBoardCollection.observe(viewLifecycleOwner) {
            for (scoresRow in it) {
                grid.addView(
                    customTextView(
                        requireContext(), scoresRow.round.toString(), 0, row, false
                    )
                )
                grid.addView(
                    customTextView(
                        requireContext(), scoresRow.playerScore.toString(), 1, row, false
                    )
                )
                grid.addView(
                    customTextView(
                        requireContext(), scoresRow.cpuScore.toString(), 2, row, false
                    )
                )
                row++
            }
        }
        vm.userScore.observe(viewLifecycleOwner) {
            grid.addView(customTextView(requireContext(), "Total", 0, row, true))
            grid.addView(customTextView(requireContext(), it.toString(), 1, row, true))
        }
        vm.computerScore.observe(viewLifecycleOwner) {
            grid.addView(customTextView(requireContext(), it.toString(), 2, row, true))
        }
    }

    private fun customTextView(
        context: Context, textString: String, column: Int, row: Int, bold: Boolean
    ): MaterialTextView {
        val textView = MaterialTextView(context).apply {
            text = textString
            if (bold) setTypeface(null, Typeface.BOLD)
            gravity = Gravity.CENTER
            layoutParams = GridLayout.LayoutParams().apply {
                width = ViewGroup.LayoutParams.WRAP_CONTENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(column, 1f)
                rowSpec = GridLayout.spec(row, 1f)
                (this as ViewGroup.MarginLayoutParams).setMargins(1, 1, 1, 1)
            }
        }
        return textView
    }


}