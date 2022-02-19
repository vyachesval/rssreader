package dev.rssreader.domain.usecase

import dev.rssreader.domain.repositories.RssChannelsRepository
import javax.inject.Inject

class IsFirstStart  @Inject constructor(val repo: RssChannelsRepository) {
    fun isFirstStart(): Boolean {
        return repo.isDataSourceCreated()
    }

}