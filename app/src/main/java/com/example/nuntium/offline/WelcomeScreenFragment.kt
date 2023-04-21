package com.example.nuntium.offline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nuntium.R
import com.example.nuntium.databinding.FragmentWelcomeScreenBinding

class WelcomeScreenFragment : Fragment() {
    lateinit var binding: FragmentWelcomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome_screen, container, false)
        binding = FragmentWelcomeScreenBinding.bind(view)

        binding.btn.setOnClickListener {
            findNavController().navigate(R.id.chooseFragment)
        }


        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}