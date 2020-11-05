package dev.rssreader.presentation.list.channels

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import dev.rssreader.R

class RssChannelListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

   @BindView(R.id.text_list_item)
   lateinit var textView: TextView

    init {
        ButterKnife.bind(this, itemView)
    }
}