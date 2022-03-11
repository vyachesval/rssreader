package dev.rssreader.rss.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.rssreader.domain.repositories.RssChannelNewsRepository
import dev.rssreader.domain.repositories.RssChannelsRepository
import dev.rssreader.entity.repositories.RssChannelNewsRepositoryImpl
import dev.rssreader.entity.repositories.RssChannelsRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class RssModule {
    @Binds
    abstract fun bindRssChannelsRepository(rssChannelsRepositoryImpl: RssChannelsRepositoryImpl): RssChannelsRepository

    @Binds
    abstract fun bindRssChannelNewsRepository(rssChannelNewsRepositoryImpl: RssChannelNewsRepositoryImpl): RssChannelNewsRepository
}