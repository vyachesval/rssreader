package dev.rssreader.domain.usecase

import dev.rssreader.domain.repositories.RssChannelsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRssChannelsList @Inject constructor(val repo: RssChannelsRepository) {
    fun getRssChannelsList(): Observable<List<String>> {
        return repo.getRssChannelsList()
    }
}