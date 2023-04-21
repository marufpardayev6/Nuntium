package com.example.nuntium.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nuntium.R
import com.example.nuntium.databinding.ItemViewBinding

class AdapterViewPejer(private val list: ArrayList<Int>) :
    RecyclerView.Adapter<AdapterViewPejer.Mh>() {

    inner class Mh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(string: Int) {
            val bind = ItemViewBinding.bind(itemView)
            bind.view.setImageResource(string)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Mh {
        return Mh(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Mh, position: Int) {
        holder.onBind(list[position])
    }

}