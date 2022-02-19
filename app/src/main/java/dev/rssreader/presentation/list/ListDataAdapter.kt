package dev.rssreader.presentation.list

import androidx.recyclerview.widget.RecyclerView

abstract class ListDataAdapter <T,TH : RecyclerView.ViewHolder> constructor(val listener: ItemClickListener<T>)
    : RecyclerView.Adapter<TH>() {

    var list: List<T> = emptyList()

    override fun getItemCount(): Int {
        return list.size
    }

    fun setNewList(newlist: List<T>) {
        list = newlist
        notifyDataSetChanged()
    }
}