package com.example.schoters.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoters.BuildConfig
import com.example.schoters.R
import com.example.schoters.view.adapter.NewsAdapter
import com.example.schoters.view_model.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        // init adapter
        newsAdapter = NewsAdapter {
            val bundle = bundleOf("news_data" to it)
            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
        rv_news.layoutManager = LinearLayoutManager(requireContext())
        rv_news.adapter = newsAdapter

        // search action, set data to adapter
        val viewModelNews = ViewModelProvider(this)[NewsViewModel::class.java]
        home_search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                home_search_view.clearFocus()
                viewModelNews.getAllNewsByKeyword(BuildConfig.API_KEY, query!!)
                viewModelNews.newsByKeyword.observe(viewLifecycleOwner) { response ->
                    jumlah_hasil_pencarian.text = "Search result: ${response.totalResults} result"
                    newsAdapter.setListNewsData(response.articles!!)
                    newsAdapter.notifyDataSetChanged()
                    home_enter_keyword.isInvisible = true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    viewModelNews.getAllNewsByKeyword(BuildConfig.API_KEY, newText)
                    viewModelNews.newsByKeyword.observe(viewLifecycleOwner) { response ->
                        jumlah_hasil_pencarian.text =
                            "Hasil pencarian: ${response.totalResults} hasil"
                        newsAdapter.setListNewsData(response.articles!!)
                        newsAdapter.notifyDataSetChanged()
                        home_enter_keyword.isInvisible = true
                    }
                }
                return true
            }

        })
    }
}