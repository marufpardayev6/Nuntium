package com.example.nuntium.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nuntium.R
import com.example.nuntium.databinding.ItemViewHPBinding

class AdapterHomePageItem(private val list: List<String>, val clic: Clic) :
    RecyclerView.Adapter<AdapterHomePageItem.MH>() {

    inner class MH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(string: String) {
            val binding = ItemViewHPBinding.bind(itemView)
            binding.text.text = string

            binding.root.setOnClickListener {
                clic.rootOnClic(binding.text)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MH {
        return MH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_h_p, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MH, position: Int) {
        holder.onBind(list[position])
    }

    interface Clic {
        fun rootOnClic(view: TextView)
    }
}