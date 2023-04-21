package com.example.nuntium.offline

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nuntium.R
import com.example.nuntium.databinding.FragmentMainkBinding


class MainkFragment : Fragment() {
    lateinit var binding: FragmentMainkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(com.example.nuntium.R.layout.fragment_maink, container, false)
        binding = FragmentMainkBinding.bind(view)

        val sherendP = activity?.getSharedPreferences("pdp", Context.MODE_PRIVATE)
        val a = sherendP?.getBoolean("name", false)
        Log.d("AAA", "onCreateView: $a")

        object : CountDownTimer(3_000, 1_000) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                if (a == true) {
                    findNavController().navigate(R.id.homePageFragment)
                } else {
                    findNavController().navigate(R.id.action_mainkFragment_to_onboardingFragment)
                }

            }

        }.start()

        return view
    }
}