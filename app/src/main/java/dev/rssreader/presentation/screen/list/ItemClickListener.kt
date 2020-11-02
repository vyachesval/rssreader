package dev.rssreader.presentation.screen.list

abstract class ItemClickListener<T> {
    abstract fun onClick(position: Int, item: T)
    abstract fun onLongClick(position: Int, item: T): Boolean
}