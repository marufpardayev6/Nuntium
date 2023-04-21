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
import com.example.nuntium.models.Article

class AdapterHomePage2(
    val list: List<Article>,
    val databaseList: List<NewsDatabase>,
    val context: Context,
    val clic: Clic,
) :
    RecyclerView.Adapter<AdapterHomePage2.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun obBind(article: Article) {
            val bind = ItemMainBinding.bind(itemView)
            Glide.with(context).load(article.urlToImage)
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .placeholder(R.drawable.maxresdefault)
                .into(bind.imgMain)
            bind.textName.text = article.title

            var isHave = false
            for (i in 0 until databaseList.size) {
                if (databaseList[i].title == article.title) {
                    isHave = true
                }
            }
            if (isHave) {
                bind.save.setBackgroundResource(R.drawable.vector__1_4)
            } else {
                bind.save.setBackgroundResource(R.drawable.vector__1_3)
            }

            bind.save.setOnClickListener {
                clic.save(article,bind.save)
            }
            bind.imgMain.setOnClickListener {
                clic.next(article)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
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