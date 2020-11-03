package dev.rssreader.presentation.screen.list

import androidx.recyclerview.widget.RecyclerView
import dev.rssreader.RssReaderApplication
import toothpick.Toothpick

abstract class ListDataAdapter <T,TH : RecyclerView.ViewHolder> constructor(val listener: ItemClickListener<T>)
    : RecyclerView.Adapter<TH>() {

    var list: List<T> = emptyList()

    init {
        Toothpick.inject(this, Toothpick.openScope(RssReaderApplication.instance))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setNewList(newlist: List<T>) {
        list = newlist
        notifyDataSetChanged()
    }
}