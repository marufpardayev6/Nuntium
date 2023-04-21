package com.example.nuntium.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.nuntium.R
import com.example.nuntium.database.NewsDatabase
import com.example.nuntium.databinding.ItemViewpegerBinding
import com.example.nuntium.models.Article
import com.example.nuntium.models.News
import com.example.nuntium.newtwork.APIClient
import com.example.nuntium.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterHomePage(
    private val list: ArrayList<String>,
    val databaseList1: List<NewsDatabase>,
    val context: Context,
    val clic: Clic,
) :
    RecyclerView.Adapter<AdapterHomePage.MyViewHolder>() {

    lateinit var adapterHomePage2: AdapterHomePage2

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun obBind(text: String) {
            val bind = ItemViewpegerBinding.bind(itemView)

            val call = APIClient.apiService.getAllNews(text, Constants.API_KEY)
            call.enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    if (response.isSuccessful) {
                        adapterHomePage2 = AdapterHomePage2(response.body()?.articles!!, databaseList1, context,
                                object : AdapterHomePage2.Clic {
                                    override fun save(article: Article, save: ImageView) {
                                        clic.save(article, save)
                                    }

                                    override fun next(article: Article) {
                                        clic.next(article)
                                    }
                                })
                        bind.ervi.adapter = adapterHomePage2
                    } else {
                        if (response.body()?.articles?.isEmpty()!!) {
                            Log.d("AAA", "onResponse: internet")
                        }
                    }
                }
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("AAA", "onFailure: ${t.message}")
                }
            })


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_viewpeger, parent, false)
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