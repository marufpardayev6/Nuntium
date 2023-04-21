package com.example.nuntium.offline

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nuntium.R
import com.example.nuntium.database.AppDatabase
import com.example.nuntium.database.LanguageDB
import com.example.nuntium.database.NewsDatabaseDao
import com.example.nuntium.databinding.FragmentChooseBinding


class ChooseFragment : Fragment() {
    private lateinit var binding: FragmentChooseBinding
    lateinit var dao: NewsDatabaseDao

    @SuppressLint("ResourceAsColor", "CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(com.example.nuntium.R.layout.fragment_choose, container, false)
        binding = FragmentChooseBinding.bind(view)

        val appDatabase = AppDatabase.getInstance(requireContext())
        dao = appDatabase.newsDatabaseDao()

        if (dao.getAllLG().isNotEmpty()) {
            dao.getAllLG().forEach {
                dao.deletLG(it)
            }
        }

        binding.apply {
            item1.setOnClickListener {
                add(text1.text.toString(), item1, text1)
            }
            item2.setOnClickListener {
                add(text2.text.toString(), item2, text2)
            }
            item3.setOnClickListener {
                add(text3.text.toString(), item3, text3)
            }
            item4.setOnClickListener {
                add(text4.text.toString(), item4, text4)
            }
            item5.setOnClickListener {
                add(text5.text.toString(), item5, text5)
            }
            item6.setOnClickListener {
                add(text6.text.toString(), item6, text6)
            }
            item7.setOnClickListener {
                add(text7.text.toString(), item7, text7)
            }
            item8.setOnClickListener {
                add(text8.text.toString(), item8, text8)
            }
            item9.setOnClickListener {
                add(text9.text.toString(), item9, text9)
            }
            item10.setOnClickListener {
                add(text10.text.toString(), item10, text10)
            }
            next.setOnClickListener {
                val sherendP = activity?.getSharedPreferences("pdp", Context.MODE_PRIVATE)
                var editer = sherendP?.edit()?.putBoolean("name", true)?.commit()
                Log.d("AAA", "onCreateView: $editer")

                if (dao.getAllLG().size >= 5) {
                    findNavController().navigate(R.id.homePageFragment)
                } else {
                    Toast.makeText(requireContext(), "Please select 5", Toast.LENGTH_SHORT).show()
                }
            }
        }


        return view
    }

    @SuppressLint("ResourceAsColor")
    private fun add(text: String, item1: LinearLayout, textView: TextView) {
        var isHave = false
        var index = -1
        for (i in 0 until dao.getAllLG().size) {
            if (dao.getAllLG()[i].language == text) {
                isHave = true
                index = i
            }
        }
        if (isHave) {
            dao.deletLG(dao.getAllLG()[index])
            item1.setBackgroundResource(R.drawable.shape_btn2)
            textView.setTextColor(Color.BLACK)
        } else {
            dao.addLG(LanguageDB((text)))
            item1.setBackgroundResource(R.drawable.shape_btn)
            textView.setTextColor(Color.WHITE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}