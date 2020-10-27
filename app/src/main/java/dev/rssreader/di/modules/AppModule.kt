package dev.rssreader.di.modules

import android.content.Context
import dev.rssreader.RssReaderApplication
import dev.rssreader.domain.repositories.RssChannelsRepository
import dev.rssreader.entity.repositories.RssChannelsRepositoryImpl
import toothpick.config.Module

class AppModule(app: RssReaderApplication) : Module() {

    init {
        bind(Context::class.java).toInstance(app)
        bind(RssChannelsRepository::class.java).to(RssChannelsRepositoryImpl::class.java).singleton()
    }
}