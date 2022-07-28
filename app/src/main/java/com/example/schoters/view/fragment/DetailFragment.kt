package com.example.schoters.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.schoters.R
import com.example.schoters.model.Article
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataNews = arguments?.getParcelable<Article>("news_data")!!

        detail_news_author.text = dataNews.author ?: "(Unknown)"
        detail_news_content.text = dataNews.content
        detail_news_description.text = dataNews.description
        detail_news_link.text = dataNews.url
        detail_news_release_date.text = dataNews.publishedAt
        detail_news_title.text = dataNews.title
        Glide.with(detail_news_image.context)
            .load(dataNews.urlToImage)
            .error(R.drawable.ic_launcher_background)
            .into(detail_news_image)
    }
}