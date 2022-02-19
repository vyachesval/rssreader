package dev.rssreader.domain.usecase

import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.domain.repositories.RssChannelsRepository
import io.reactivex.Completable
import javax.inject.Inject

class DelRssChannel @Inject constructor(val repo: RssChannelsRepository) {

    fun delRssChannel(rsschannel: RssChannelData) : Completable {
        return repo.delRssChannel(rsschannel.id)
    }
}