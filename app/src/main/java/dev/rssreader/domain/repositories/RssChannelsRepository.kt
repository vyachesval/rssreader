package dev.rssreader.domain.repositories

interface RssChannelsRepository {
    fun addRssChannel(rsschannel: String)
}