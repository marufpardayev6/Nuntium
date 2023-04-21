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
import com.example.nuntium.databinding.ItemSave2Binding
import com.example.nuntium.models.Article
import com.example.nuntium.models.News

class AdapterHomePage4(
    val list: List<Article>,
    val databaseList: List<NewsDatabase>,
    val context: Context,
    val clic: Clic,
) :
    RecyclerView.Adapter<AdapterHomePage4.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun obBind(article: Article) {
            val bind = ItemSave2Binding.bind(itemView)
            Glide.with(context).load(article.urlToImage).into(bind.img)
            bind.text.text = article.title


            var isHave = false
            for (element in databaseList) {
                if (element.author == article.author) {
                    isHave = true
                }
            }
            if (isHave) {
                bind.savebtn.setBackgroundResource(R.drawable.vector__1_4)
            } else {
                bind.savebtn.setBackgroundResource(R.drawable.vector__1_3)
            }

            bind.savebtn.setOnClickListener {
                clic.save(article, bind.savebtn)
            }
            bind.root.setOnClickListener {
                clic.next(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_save2, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.obBind(list[position])
    }

    interface Clic {
        fun save(article: Article, save: ImageView)
        fun next(article: Article)
    }
}