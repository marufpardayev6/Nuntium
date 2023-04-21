package com.example.nuntium.onlilefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.nuntium.R
import com.example.nuntium.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    lateinit var binding: FragmentUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        binding = FragmentUserBinding.bind(view)

        val sharedPreferences = activity?.getSharedPreferences("Mode", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        val nightMode = sharedPreferences?.getBoolean("night", false)

        if (nightMode == true) {
            binding.btnswift.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }



        binding.btnswift.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor?.putBoolean("night", false)
                editor?.apply()

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor?.putBoolean("night", true)
                editor?.apply()
            }
        }
//        binding.layaout2.setOnClickListener {
//            findNavController().navigate(R.id.action_userFragment_to_language2)
//        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}