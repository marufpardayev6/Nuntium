package com.example.nuntium.onlilefragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.nuntium.R
import com.example.nuntium.adapter.AdapterHomePage2
import com.example.nuntium.database.AppDatabase
import com.example.nuntium.database.NewsDatabase
import com.example.nuntium.database.NewsDatabaseDao
import com.example.nuntium.databinding.FragmentArticlePageBinding
import com.example.nuntium.models.Article
import com.example.nuntium.models.News
import com.example.nuntium.newtwork.APIClient
import com.example.nuntium.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlePageFragment : Fragment() {
    lateinit var binding: FragmentArticlePageBinding
    private lateinit var newsDatabase: NewsDatabase
    private lateinit var adapterHomePage2: AdapterHomePage2
    lateinit var appDatabase: AppDatabase
    lateinit var dao: NewsDatabaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article_page, container, false)
        binding = FragmentArticlePageBinding.bind(view)

        newsDatabase = arguments?.getSerializable("next") as NewsDatabase

        appDatabase = AppDatabase.getInstance(requireContext())
        val dao1: NewsDatabaseDao = appDatabase.newsDatabaseDao()
        dao = dao1

        Glide.with(requireContext()).load(newsDatabase.urlToImage).into(binding.img)
        binding.collapsingToolbar.title = newsDatabase.title
        binding.text1.text = "Author  -  ${newsDatabase.author}"
        binding.text2.text = "Description  -  ${newsDatabase.description}"
        binding.text3.text = "Content  -  ${newsDatabase.content}"
        binding.text4.text = "Published At  -  ${newsDatabase.publishedAt}"

        Log.d("AAA", "onCreateView: $newsDatabase")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_toolbar -> {
                var isHave = false
                var index = -1
                for (i in 0 until dao.getAllNews().size) {
                    if (dao.getAllNews()[i].author == newsDatabase.author) {
                        isHave = true
                        index = i
                    }
                }
                if (isHave) {
                    val a = dao.getAllNews()[index]
                    a.databse = false
                    dao.updateNews(a)
                    if (dao.getAllNews()[index].databse == false) {
                        item.setIcon(R.drawable.vector__1_3)
                    }
                    dao.deleteNews(dao.getAllNews()[index])
                } else {
                    newsDatabase.databse = true
                    if (newsDatabase.databse!!) {
                        item.setIcon(R.drawable.vector__1_4)
                    }
                    dao.addNews(newsDatabase)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)

        var isHave = false
        for (i in 0 until dao.getAllNews().size) {
            if (dao.getAllNews()[i].author == newsDatabase.author) {
                isHave = true
            }
        }
        if (isHave) {
            menu.findItem(R.id.save_toolbar).setIcon(R.drawable.vector__1_4)
        } else {
            menu.findItem(R.id.save_toolbar).setIcon(R.drawable.vector__1_3)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

}