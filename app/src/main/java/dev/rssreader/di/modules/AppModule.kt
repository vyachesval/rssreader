package dev.rssreader.di.modules

import android.content.Context
import dev.rssreader.RssReaderApplication
import dev.rssreader.domain.repositories.RssChannelNewsRepository
import dev.rssreader.domain.repositories.RssChannelsRepository
import dev.rssreader.entity.repositories.RssChannelNewsRepositoryImpl
import dev.rssreader.entity.repositories.RssChannelsRepositoryImpl
import dev.rssreader.entity.repositories.datasource.LocalDataSource
import toothpick.config.Module

class AppModule(app: RssReaderApplication) : Module() {

    init {
        bind(Context::class.java).toInstance(app)
        bind(RssChannelsRepository::class.java).to(RssChannelsRepositoryImpl::class.java).singleton()
        bind(RssChannelNewsRepository::class.java).to(RssChannelNewsRepositoryImpl::class.java).singleton()
        bind(LocalDataSource::class.java).singleton()
    }
}