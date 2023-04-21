package com.example.nuntium.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nuntium.R
import com.example.nuntium.database.NewsDatabase
import com.example.nuntium.databinding.ItemMainBinding
import com.example.nuntium.databinding.ItemSaveBinding
import com.example.nuntium.models.Article

class AdapterHomePage3(
    val databaseList: List<NewsDatabase>,
    val context: Context,
    val clic: Clic,
) :
    RecyclerView.Adapter<AdapterHomePage3.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun obBind(article: NewsDatabase) {
            val bind = ItemSaveBinding.bind(itemView)

            Glide.with(context).load(article.urlToImage).into(bind.imgMain)
            bind.textName.text = article.title

            var isHave = false
            for (element in databaseList) {
                if (element.databse == true) {
                    isHave = true
                }
            }
            if (isHave) {
                bind.save.setBackgroundResource(R.drawable.vector__1_4)
            } else {
                bind.save.setBackgroundResource(R.drawable.vector__1_3)
            }

            bind.save.setOnClickListener {
                clic.save(article, bind.save)
            }
            bind.parent.setOnClickListener {
                clic.next(article)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_save, parent, false)
        )
    }

    override fun getItemCount(): Int = databaseList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.obBind(databaseList[position])
    }

    interface Clic {
        fun save(article: NewsDatabase, save: ImageView)
        fun next(article: NewsDatabase)
    }
}