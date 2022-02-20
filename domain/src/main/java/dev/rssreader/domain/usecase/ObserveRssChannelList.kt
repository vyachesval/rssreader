package dev.rssreader.domain.usecase

import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.domain.repositories.RssChannelsRepository
import io.reactivex.Observable
import javax.inject.Inject

class ObserveRssChannelList @Inject constructor(val repo: RssChannelsRepository) {
    fun observeRssChannelList(): Observable<RssChannelData> {
        return repo.observeRssChannelList()
    }
}