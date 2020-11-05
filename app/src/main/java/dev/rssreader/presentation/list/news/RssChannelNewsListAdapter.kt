package dev.rssreader.presentation.list.news

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.rssreader.R
import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.presentation.list.ItemClickListener
import dev.rssreader.presentation.list.ListDataAdapter

class RssChannelNewsListAdapter constructor(listener: ItemClickListener<RssChannelNewsData>)
    : ListDataAdapter<RssChannelNewsData, RssChannelNewsListViewHolder>(listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssChannelNewsListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rsschannelnews_list_item, parent, false)
        return RssChannelNewsListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RssChannelNewsListViewHolder, position: Int) {
        holder.headerView.text = list[position].title
        holder.descriptionView.text = list[position].description
        holder.dtView.text = list[position].date.toString()
        holder.linkView.text = list[position].link
        holder.itemView.setOnClickListener{ listener.onClick(position, list[position]) }
        holder.itemView.setOnLongClickListener{ listener.onLongClick(position, list[position]) }
    }
}