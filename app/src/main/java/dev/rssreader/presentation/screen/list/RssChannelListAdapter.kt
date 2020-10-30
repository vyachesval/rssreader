package dev.rssreader.presentation.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.rssreader.R
import dev.rssreader.domain.entity.RssChannelData

class RssChannelListAdapter : RecyclerView.Adapter<RssChannelListViewHolder>() {

    var rsschannels: List<RssChannelData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssChannelListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rsschannel_list_item, parent, false)
        return RssChannelListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RssChannelListViewHolder, position: Int) {
        holder.textView.text = rsschannels[position].address
    }

    override fun getItemCount(): Int {
        return rsschannels.size
    }

    fun setList(newrsschannels: List<RssChannelData>) {
        rsschannels = newrsschannels
        notifyDataSetChanged()
    }
}