package com.example.kima.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import com.example.kima.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

/**
 * A popup displaying the rules of the game.
 */
class RulesDialogFragment : DialogFragment() {
    lateinit var tvInfoText: MaterialTextView
    lateinit var btnCloseDialog: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_rules, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       btnCloseDialog = view.findViewById(R.id.btn_close_dialog)
        tvInfoText = view.findViewById(R.id.tv_info)
        tvInfoText.text = getString(R.string.rules_dialog_info_text).trimIndent()
        btnCloseDialog.setOnClickListener {
            dialog?.dismiss()
        }
    }
}