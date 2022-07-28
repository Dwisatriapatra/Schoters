package com.example.schoters.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schoters.R
import com.example.schoters.model.Article
import kotlinx.android.synthetic.main.item_news_adapter.view.*


class NewsAdapter (private val onClick: (Article) -> Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    private var listNews: List<Article>? = null
    fun setListNewsData(list: List<Article>){
        this.listNews = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_adapter, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView){
            if(listNews!![position].author.isNullOrEmpty()){
                card_news_author.text = "Author: (unknown)"
            }else{
                card_news_author.text = "Author: ${listNews!![position].author}"
            }

            card_news_release_date.text = "Release date: ${listNews!![position].publishedAt}"
            card_news_title.text = listNews!![position].title
            Glide.with(card_news_image.context)
                .load(listNews!![position].urlToImage)
                .error(R.drawable.ic_launcher_background)
                .into(card_news_image)

            //on click action
            card_news.setOnClickListener {
                onClick(listNews!![position])
            }
        }
    }

    override fun getItemCount(): Int {
        return if(listNews.isNullOrEmpty()){
            0
        }else{
            listNews!!.size
        }
    }
}