package com.example.kima.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.kima.R
import com.example.kima.databinding.FragmentPlayedCardBinding
import com.example.kima.viewmodel.GameViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [PlayedCardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayedCardFragment : Fragment() {
    private lateinit var binding: FragmentPlayedCardBinding
    lateinit var vm: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vm = ViewModelProvider(requireActivity())[GameViewModel::class.java]
        binding = FragmentPlayedCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.player.observe(viewLifecycleOwner) {

            binding.ivPlayedCard.setImageResource(it.playedCard?.id ?: R.drawable.ace_spades) //TODO Byt ut Ace of Spades mot baksidan av kort
        }

    }


}