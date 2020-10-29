package dev.rssreader.domain.repositories

import io.reactivex.Completable
import io.reactivex.Observable

interface RssChannelsRepository {
    fun addRssChannel(rsschannel: String): Completable
    fun getRssChannelsList() : Observable<List<String>>
}