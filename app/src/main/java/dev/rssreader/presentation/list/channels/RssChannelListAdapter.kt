package dev.rssreader.presentation.list.channels

import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso

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
        holder.addressView.text = list[position].address
        if(list[position].title.isNotEmpty()) {
            holder.titleView.text = list[position].title
        }
        if(list[position].image.isNotEmpty()) {
            Picasso.get().load(list[position].image).into(holder.imageView)
        }
        holder.itemView.setOnClickListener{
            listener.onClick(position, list[position])
        }
        holder.itemView.setOnLongClickListener{
            listener.onLongClick(position, list[position])
        }
    }
}