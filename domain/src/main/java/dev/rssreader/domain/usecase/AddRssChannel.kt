package dev.rssreader.domain.usecase

import dev.rssreader.domain.repositories.RssChannelsRepository
import io.reactivex.Completable
import javax.inject.Inject

class AddRssChannel @Inject constructor(val repo: RssChannelsRepository) {

    fun addRssChannel(rsschannel: String) : Completable {
        return repo.addRssChannel(rsschannel)
    }
}