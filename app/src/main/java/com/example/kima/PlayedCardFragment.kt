package com.example.kima

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kima.databinding.FragmentPlayedCardBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [PlayedCardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayedCardFragment: Fragment() {
    private lateinit var binding: FragmentPlayedCardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayedCardBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


}