package dev.rssreader.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rssreader.RssReaderApplication

import toothpick.Toothpick
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        Toothpick.openScope(RssReaderApplication.instance).getInstance(modelClass) as T
}
