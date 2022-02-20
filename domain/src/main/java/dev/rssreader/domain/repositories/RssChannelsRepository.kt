package dev.rssreader.domain.repositories

import dev.rssreader.domain.entity.RssChannelData
import io.reactivex.Completable
import io.reactivex.Observable

interface RssChannelsRepository {
    fun addRssChannel(rsschannel: String): Completable
    fun delRssChannel(rsschannelId: Int): Completable
    fun getRssChannelsList() : Observable<List<RssChannelData>>
    fun addRssChannelsList(list: Array<String>) : Completable

    fun observeRssChannelList(): Observable<RssChannelData>

    fun isDataSourceCreated(): Observable<Boolean>
}