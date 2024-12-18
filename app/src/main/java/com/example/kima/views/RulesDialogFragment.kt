package com.example.kima.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kima.R
import com.google.android.material.button.MaterialButton

/**
 * A popup displaying the rules of the game.
 */
class RulesDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_rules, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCloseView = view.findViewById<MaterialButton>(R.id.btn_close_dialog)
        btnCloseView.setOnClickListener {
            dialog?.dismiss()
        }
    }
}