package dev.rssreader.di.modules

import android.content.Context
import dev.rssreader.RssReaderApplication
import toothpick.config.Module

class AppModule(app: RssReaderApplication) : Module() {

    init {
        bind(Context::class.java).toInstance(app)
    }
}