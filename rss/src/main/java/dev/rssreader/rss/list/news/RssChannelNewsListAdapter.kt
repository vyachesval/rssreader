package dev.rssreader.rss.list.news

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import dev.rssreader.rss.R
import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.rss.list.ItemClickListener
import dev.rssreader.rss.list.ListDataAdapter
import java.time.format.DateTimeFormatter

class RssChannelNewsListAdapter constructor(listener: ItemClickListener<RssChannelNewsData>)
    : ListDataAdapter<RssChannelNewsData, RssChannelNewsListViewHolder>(listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssChannelNewsListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.rsschannelnews_list_item,
            parent,
            false
        )
        return RssChannelNewsListViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RssChannelNewsListViewHolder, position: Int) {
        setViewText(holder.headerView, list[position].title)
        setViewText(holder.descriptionView, list[position].description)
        setViewText(holder.dtView, list[position].date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")))
        setViewText(holder.linkView, list[position].link)
        holder.itemView.setOnClickListener{ listener.onClick(position, list[position]) }
        holder.itemView.setOnLongClickListener{ listener.onLongClick(position, list[position]) }
    }

    private fun setViewText(view: TextView, text: String) {
        view.text = text
    }
}