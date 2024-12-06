package com.example.kima.views

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.kima.R
import com.example.kima.viewmodel.GameViewModel
import com.example.kima.databinding.DialogScoreboardBinding
import com.google.android.material.textview.MaterialTextView

/**
 * A popup displaying the scoreboard.
 */
class ScoreboardDialogFragment : DialogFragment() {
    private lateinit var binding: DialogScoreboardBinding
    private lateinit var vm: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        vm = ViewModelProvider(requireActivity())[GameViewModel::class.java]
        binding = DialogScoreboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val grid = binding.gridScoreboard
        var row = 1 // 0 is title row in the xml-file.

        // Instead of having a great deal of textviews in the xml-file to access.
        vm.scoreBoardCollection.observe(viewLifecycleOwner) {
            for (scoresRow in it) { // Adds a row of text views for every trick played. Three different columns.
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
        vm.player.observe(viewLifecycleOwner) {
            grid.addView(customTextView(requireContext(), "Total", 0, row, true))
            grid.addView(customTextView(requireContext(), it.score.toString(), 1, row, true))
        }
        vm.computer.observe(viewLifecycleOwner) {
            grid.addView(customTextView(requireContext(), it.score.toString(), 2, row, true))
        }

        binding.btnClose.setOnClickListener {
            (activity as GameActivity).finish()
        }
    }

    private fun customTextView(
        context: Context, textString: String, column: Int, row: Int, bold: Boolean
    ): MaterialTextView {
        // Layout code for the scoreboard.
        val textView = MaterialTextView(ContextThemeWrapper(context, R.style.TVScoreboard)).apply {
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