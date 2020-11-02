package dev.rssreader.presentation.screen.list

interface ItemClickListener<T> {
    fun onClick(position: Int, item: T)
    fun onLongClick(position: Int, item: T): Boolean
}