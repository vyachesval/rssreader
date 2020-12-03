package dev.rssreader.domain.usecase

import dev.rssreader.domain.repositories.RssChannelsRepository
import io.reactivex.Completable
import javax.inject.Inject

class AddRssChannelsList @Inject constructor(private val repo: RssChannelsRepository) {

    fun addRssChannelsList(rsschannels: Array<String>): Completable {
        return repo.addRssChannelsList(rsschannels)
    }
}