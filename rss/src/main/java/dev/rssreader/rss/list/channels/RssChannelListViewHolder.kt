package dev.rssreader.rss.list.channels

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import dev.rssreader.rss.R2

class RssChannelListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

   @BindView(R2.id.address_list_item)
   lateinit var addressView: TextView

    @BindView(R2.id.title_list_item)
    lateinit var titleView: TextView

    @BindView(R2.id.image_list_item)
    lateinit var imageView: ImageView

    init {
        ButterKnife.bind(this, itemView)
    }
}