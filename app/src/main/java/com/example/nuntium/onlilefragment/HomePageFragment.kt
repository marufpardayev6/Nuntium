package com.example.nuntium.onlilefragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nuntium.R
import com.example.nuntium.adapter.AdapterHomePage
import com.example.nuntium.adapter.AdapterHomePage4
import com.example.nuntium.database.AppDatabase
import com.example.nuntium.database.NewsDatabase
import com.example.nuntium.database.NewsDatabaseDao
import com.example.nuntium.databinding.FragmentHomePageBinding
import com.example.nuntium.models.Article
import com.example.nuntium.models.News
import com.example.nuntium.newtwork.APIClient
import com.example.nuntium.utils.Constants
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class HomePageFragment : Fragment() {
    lateinit var binding: FragmentHomePageBinding
    lateinit var adapter: AdapterHomePage
    lateinit var appDatabase: AppDatabase
    lateinit var arrayList: ArrayList<NewsDatabase>
    lateinit var adapterHomePage4: AdapterHomePage4
    lateinit var dao: NewsDatabaseDao
    var text: String = "all"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)
        binding = FragmentHomePageBinding.bind(view)
        arrayList = ArrayList()

        val sharedPreferences = activity?.getSharedPreferences("Mode", Context.MODE_PRIVATE)
        val nightMode = sharedPreferences?.getBoolean("night", false)

        if (nightMode == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }


        appDatabase = AppDatabase.getInstance(requireContext())
        dao = appDatabase.newsDatabaseDao()

        adapter = AdapterHomePage(
            load(),
            dao.getAllNews(),
            requireContext(),
            object : AdapterHomePage.Clic {
                override fun save(article: Article, save: ImageView) {
                    val news = NewsDatabase(
                        article.author,
                        article.content,
                        article.description,
                        article.publishedAt,
                        article.title,
                        article.url,
                        article.urlToImage,
                        databse = false
                    )

                    var isHave = false
                    var index = -1
                    for (i in 0 until dao.getAllNews().size) {
                        if (dao.getAllNews()[i].title == article.title) {
                            isHave = true
                            index = i
                        }
                    }
                    if (isHave) {
                        val a = dao.getAllNews()[index]
                        a.databse = false
                        dao.updateNews(a)
                        if (dao.getAllNews()[index].databse == false) {
                            save.setBackgroundResource(R.drawable.vector__1_3)
                        }
                        dao.deleteNews(dao.getAllNews()[index])
                    } else {
                        news.databse = true
                        if (news.databse!!) {
                            save.setBackgroundResource(R.drawable.vector__1_4)
                        }
                        dao.addNews(news)
                    }
                }

                override fun next(article: Article) {
                    val newsDatabase = NewsDatabase(
                        article.author,
                        article.content,
                        article.description,
                        article.publishedAt,
                        article.title,
                        article.url,
                        article.urlToImage,
                        databse = false
                    )

                    val bundle = Bundle()
                    bundle.putSerializable("next", newsDatabase)
                    findNavController().navigate(R.id.articlePageFragment, bundle)
                }
            })
        binding.ervi2.adapter = adapter

        TabLayoutMediator(binding.ervi1, binding.ervi2) { tab, pos ->
            tab.text = load()[pos]
        }.attach()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                search(p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        binding.searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                binding.ervi2.visibility = View.VISIBLE
                binding.ervi1.visibility = View.VISIBLE
                binding.ervi3.visibility = View.GONE
                return false
            }
        })



        return view
    }

    private fun search(text: String?) {
        val call = APIClient.apiService.getAllNews(text, Constants.API_KEY)
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    binding.ervi1.visibility = View.GONE
                    binding.ervi2.visibility = View.GONE
                    binding.ervi3.visibility = View.VISIBLE

                    adapterHomePage4 = AdapterHomePage4(response.body()?.articles!!,
                        dao.getAllNews(),
                        requireContext(),
                        object : AdapterHomePage4.Clic {
                            override fun save(article: Article, save: ImageView) {
                                val news = NewsDatabase(
                                    article.author,
                                    article.content,
                                    article.description,
                                    article.publishedAt,
                                    article.title,
                                    article.url,
                                    article.urlToImage,
                                    databse = false
                                )

                                var isHave = false
                                var index = -1
                                for (i in 0 until dao.getAllNews().size) {
                                    if (dao.getAllNews()[i].author == article.author) {
                                        isHave = true
                                        index = i
                                    }
                                }
                                if (isHave) {
                                    val a1 = dao.getAllNews()[index]
                                    a1.databse = false
                                    dao.updateNews(a1)
                                    if (dao.getAllNews()[index].databse == false) {
                                        save.setBackgroundResource(R.drawable.vector__1_3)
                                    }
                                    dao.deleteNews(dao.getAllNews()[index])
                                } else {
                                    news.databse = true
                                    if (news.databse!!) {
                                        save.setBackgroundResource(R.drawable.vector__1_4)
                                    }
                                    dao.addNews(news)
                                }
                            }

                            override fun next(article: Article) {

                                var isHave = false
                                var index = -1
                                for (i in 0 until dao.getAllNews().size) {
                                    if (dao.getAllNews()[i].author == article.author) {
                                        isHave = true
                                        index = i
                                    }
                                }
                                if (isHave) {
                                    val newsDatabase = NewsDatabase(
                                        article.author,
                                        article.content,
                                        article.description,
                                        article.publishedAt,
                                        article.title,
                                        article.url,
                                        article.urlToImage,
                                        databse = true
                                    )

                                    val bundle = Bundle()
                                    bundle.putSerializable("next", newsDatabase)
                                    findNavController().navigate(R.id.articlePageFragment, bundle)
                                } else {
                                    val newsDatabase = NewsDatabase(
                                        article.author,
                                        article.content,
                                        article.description,
                                        article.publishedAt,
                                        article.title,
                                        article.url,
                                        article.urlToImage,
                                        databse = false
                                    )

                                    val bundle = Bundle()
                                    bundle.putSerializable("next", newsDatabase)
                                    findNavController().navigate(R.id.articlePageFragment, bundle)
                                }


                            }
                        })
                    binding.ervi3.adapter = adapterHomePage4
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {

            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    private fun load(): ArrayList<String> {
        val arrayList = ArrayList<String>()
        arrayList.add("All")
        dao.getAllLG().forEach {
            arrayList.add(it.language.toString())
        }
        return arrayList
    }

}