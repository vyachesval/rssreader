package dev.rssreader.presentation.list.news

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import dev.rssreader.R

class RssChannelNewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.text_news_header)
    lateinit var headerView: TextView

    @BindView(R.id.text_news_description)
    lateinit var descriptionView: TextView

    @BindView(R.id.text_news_dt)
    lateinit var dtView: TextView

    @BindView(R.id.text_news_link)
    lateinit var linkView: TextView

    init {
        ButterKnife.bind(this, itemView)
    }
}