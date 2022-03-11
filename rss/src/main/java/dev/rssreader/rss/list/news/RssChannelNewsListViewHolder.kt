package dev.rssreader.rss.list.news

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import dev.rssreader.rss.R2

class RssChannelNewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R2.id.text_news_header)
    lateinit var headerView: TextView

    @BindView(R2.id.text_news_description)
    lateinit var descriptionView: TextView

    @BindView(R2.id.text_news_dt)
    lateinit var dtView: TextView

    @BindView(R2.id.text_news_link)
    lateinit var linkView: TextView

    init {
        ButterKnife.bind(this, itemView)
    }
}