package dev.rssreader.presentation.list.channels

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.rssreader.R
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.presentation.list.ItemClickListener
import dev.rssreader.presentation.list.ListDataAdapter

class RssChannelListAdapter constructor(listener: ItemClickListener<RssChannelData>)
    : ListDataAdapter<RssChannelData, RssChannelListViewHolder>(listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssChannelListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rsschannel_list_item, parent, false)
        return RssChannelListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RssChannelListViewHolder, position: Int) {
        holder.textView.text = list[position].address
        holder.itemView.setOnClickListener{ listener.onClick(position, list[position]) }
        holder.itemView.setOnLongClickListener{ listener.onLongClick(position, list[position]) }
    }
}