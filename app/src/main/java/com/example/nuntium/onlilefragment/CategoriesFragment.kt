package com.example.nuntium.onlilefragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.nuntium.R
import com.example.nuntium.database.AppDatabase
import com.example.nuntium.database.LanguageDB
import com.example.nuntium.database.NewsDatabaseDao
import com.example.nuntium.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {
    lateinit var binding: FragmentCategoriesBinding
    lateinit var database: AppDatabase
    lateinit var dao: NewsDatabaseDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        binding = FragmentCategoriesBinding.bind(view)

        database = AppDatabase.getInstance(requireContext())
        dao = database.newsDatabaseDao()


        // tekshirish
        for (i in 0 until dao.getAllLG().size) {
            when (dao.getAllLG()[i].language) {
                "Sport" -> {
                    binding.item1.setBackgroundResource(R.drawable.shape_btn)
                    binding.text1.setTextColor(Color.WHITE)
                }
                "Politics" -> {
                    binding.item2.setBackgroundResource(R.drawable.shape_btn)
                    binding.text2.setTextColor(Color.WHITE)
                }
                "Life" -> {
                    binding.item3.setBackgroundResource(R.drawable.shape_btn)
                    binding.text3.setTextColor(Color.WHITE)
                }
                "Gaming" -> {
                    binding.item4.setBackgroundResource(R.drawable.shape_btn)
                    binding.text4.setTextColor(Color.WHITE)
                }
                "Animals" -> {
                    binding.item5.setBackgroundResource(R.drawable.shape_btn)
                    binding.text5.setTextColor(Color.WHITE)
                }
                "Nature" -> {
                    binding.item6.setBackgroundResource(R.drawable.shape_btn)
                    binding.text6.setTextColor(Color.WHITE)
                }
                "Food" -> {
                    binding.item7.setBackgroundResource(R.drawable.shape_btn)
                    binding.text7.setTextColor(Color.WHITE)
                }
                "Art" -> {
                    binding.item8.setBackgroundResource(R.drawable.shape_btn)
                    binding.text8.setTextColor(Color.WHITE)
                }
                "History" -> {
                    binding.item9.setBackgroundResource(R.drawable.shape_btn)
                    binding.text9.setTextColor(Color.WHITE)
                }
                "Fashion" -> {
                    binding.item10.setBackgroundResource(R.drawable.shape_btn)
                    binding.text10.setTextColor(Color.WHITE)
                }
            }
        }
//
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
        }

        return view
    }

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