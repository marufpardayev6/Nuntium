package com.example.nuntium.offline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.nuntium.R
import com.example.nuntium.adapter.AdapterViewPejer
import com.example.nuntium.databinding.FragmentOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator


class OnboardingFragment : Fragment() {
    lateinit var binding: FragmentOnboardingBinding
    lateinit var list: ArrayList<Int>
    lateinit var adapter: AdapterViewPejer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)
        binding = FragmentOnboardingBinding.bind(view)
        listAdd()

        adapter = AdapterViewPejer(list)
        binding.viewPejer.adapter = adapter

        setUpTransformer()

        TabLayoutMediator(binding.TaLa, binding.viewPejer) { tab, position ->
            when (position) {
                0 -> {

                }
                1 -> {

                }
                2 -> {

                }
            }
        }.attach()

        binding.next.setOnClickListener {
            val x = binding.viewPejer.currentItem
            if (x == 2) {
                findNavController().navigate(R.id.welcomeScreenFragment)
            }
            val newPosition = x + 1
            binding.viewPejer.currentItem = newPosition
        }

        return view
    }

    private fun listAdd() {
        list = ArrayList()

        list.add(R.drawable.foto1)
        list.add(R.drawable.foto2)
        list.add(R.drawable.foto3)
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        binding.viewPejer.setPageTransformer(transformer)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

}