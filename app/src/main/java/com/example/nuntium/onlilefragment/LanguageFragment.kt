package com.example.nuntium.onlilefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nuntium.R
import com.example.nuntium.database.AppDatabase
import com.example.nuntium.database.ForLanguages
import com.example.nuntium.database.NewsDatabaseDao
import com.example.nuntium.databinding.FragmentLanguageBinding

class LanguageFragment : Fragment() {
    lateinit var binding: FragmentLanguageBinding
    lateinit var database: AppDatabase
    lateinit var dao: NewsDatabaseDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_language, container, false)
        binding = FragmentLanguageBinding.bind(view)
        database = AppDatabase.getInstance(requireContext())
        dao = database.newsDatabaseDao()

        for (i in 0 until dao.getForAllLG().size){
            when (dao.getForAllLG()[i].tongue){
                "English" -> {
                    binding.layaout1.setBackgroundResource(R.drawable.shape_btn)
                    binding.layaout2.setBackgroundResource(R.drawable.shape_btn2)
                    binding.layaout3.setBackgroundResource(R.drawable.shape_btn2)
                    binding.layaout4.setBackgroundResource(R.drawable.shape_btn2)
                }
                "Turkish" -> {
                    binding.layaout1.setBackgroundResource(R.drawable.shape_btn2)
                    binding.layaout2.setBackgroundResource(R.drawable.shape_btn)
                    binding.layaout3.setBackgroundResource(R.drawable.shape_btn2)
                    binding.layaout4.setBackgroundResource(R.drawable.shape_btn2)
                }
                "German" -> {
                    binding.layaout1.setBackgroundResource(R.drawable.shape_btn2)
                    binding.layaout2.setBackgroundResource(R.drawable.shape_btn2)
                    binding.layaout3.setBackgroundResource(R.drawable.shape_btn)
                    binding.layaout4.setBackgroundResource(R.drawable.shape_btn2)
                }
                "Spanish" -> {
                    binding.layaout1.setBackgroundResource(R.drawable.shape_btn2)
                    binding.layaout2.setBackgroundResource(R.drawable.shape_btn2)
                    binding.layaout3.setBackgroundResource(R.drawable.shape_btn2)
                    binding.layaout4.setBackgroundResource(R.drawable.shape_btn)
                }
            }
        }

        binding.layaout1.setOnClickListener {
            addDAtabase(binding.textLanguage1.text.toString())
            binding.layaout1.setBackgroundResource(R.drawable.shape_btn)
            binding.layaout2.setBackgroundResource(R.drawable.shape_btn2)
            binding.layaout3.setBackgroundResource(R.drawable.shape_btn2)
            binding.layaout4.setBackgroundResource(R.drawable.shape_btn2)
        }
        binding.layaout2.setOnClickListener {
            addDAtabase(binding.textLanguage2.text.toString())
            binding.layaout1.setBackgroundResource(R.drawable.shape_btn2)
            binding.layaout2.setBackgroundResource(R.drawable.shape_btn)
            binding.layaout3.setBackgroundResource(R.drawable.shape_btn2)
            binding.layaout4.setBackgroundResource(R.drawable.shape_btn2)
        }
        binding.layaout3.setOnClickListener {
            addDAtabase(binding.textLanguage3.text.toString())
            binding.layaout1.setBackgroundResource(R.drawable.shape_btn2)
            binding.layaout2.setBackgroundResource(R.drawable.shape_btn2)
            binding.layaout3.setBackgroundResource(R.drawable.shape_btn)
            binding.layaout4.setBackgroundResource(R.drawable.shape_btn2)
        }
        binding.layaout4.setOnClickListener {
            addDAtabase(binding.textLanguage4.text.toString())
            binding.layaout1.setBackgroundResource(R.drawable.shape_btn2)
            binding.layaout2.setBackgroundResource(R.drawable.shape_btn2)
            binding.layaout3.setBackgroundResource(R.drawable.shape_btn2)
            binding.layaout4.setBackgroundResource(R.drawable.shape_btn)
        }


        return view
    }

    private fun addDAtabase(text: String) {
        dao.getForAllLG().forEach {
            dao.deletForLG(it)
        }
        dao.addForLG(ForLanguages(text))
    }
}