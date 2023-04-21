package com.example.nuntium.onlilefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.nuntium.R
import com.example.nuntium.adapter.AdapterHomePage3
import com.example.nuntium.database.AppDatabase
import com.example.nuntium.database.NewsDatabase
import com.example.nuntium.database.NewsDatabaseDao
import com.example.nuntium.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment() {
    lateinit var binding: FragmentBookmarksBinding
    lateinit var database: AppDatabase
    lateinit var adapter: AdapterHomePage3
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bookmarks, container, false)
        binding = FragmentBookmarksBinding.bind(view)

        database = AppDatabase.getInstance(requireContext())
        val dao: NewsDatabaseDao = database.newsDatabaseDao()

        if (dao.getAllNews().isNotEmpty()) {
            binding.img.visibility = View.GONE
            binding.img2.visibility = View.GONE
            binding.img3.visibility = View.GONE
            binding.ervi.visibility = View.VISIBLE

            adapter =
                AdapterHomePage3(
                    dao.getAllNews(),
                    requireContext(),
                    object : AdapterHomePage3.Clic {
                        override fun save(article: NewsDatabase, save: ImageView) {

                            var isHave = false
                            var index = -1
                            for (i in 0 until dao.getAllNews().size) {
                                if (dao.getAllNews()[i].author == article.author) {
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
                                article.databse = true
                                if (article.databse!!) {
                                    save.setBackgroundResource(R.drawable.vector__1_4)
                                }
                                dao.addNews(article)
                            }
                        }

                        override fun next(article: NewsDatabase) {
                            val bundle = Bundle()
                            bundle.putSerializable("next", article)
                            findNavController().navigate(R.id.articlePageFragment, bundle)
                        }
                    })
            binding.ervi.adapter = adapter
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}