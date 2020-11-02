package dev.rssreader.presentation.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.rssreader.R
import dev.rssreader.RssReaderApplication
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.domain.usecase.DelRssChannel
import toothpick.Toothpick
import javax.inject.Inject

class RssChannelListAdapter constructor(val listener: ItemClickListener<RssChannelData>)
    : RecyclerView.Adapter<RssChannelListViewHolder>() {

    var rsschannels: List<RssChannelData> = emptyList()

    @Inject
    lateinit var delRssChannel: DelRssChannel

    init {
        Toothpick.inject(this, Toothpick.openScope(RssReaderApplication.instance))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssChannelListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rsschannel_list_item, parent, false)
        return RssChannelListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RssChannelListViewHolder, position: Int) {
        holder.textView.text = rsschannels[position].address
        holder.itemView.setOnClickListener{ listener.onClick(position, rsschannels[position]) }
        holder.itemView.setOnLongClickListener{ listener.onLongClick(position, rsschannels[position]) }
    }

    override fun getItemCount(): Int {
        return rsschannels.size
    }

    fun setList(newrsschannels: List<RssChannelData>) {
        rsschannels = newrsschannels
        notifyDataSetChanged()
    }
}