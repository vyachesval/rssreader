package dev.rssreader.domain.usecase

import dev.rssreader.domain.repositories.RssChannelsRepository
import io.reactivex.Observable

import javax.inject.Inject

class IsFirstStart  @Inject constructor(val repo: RssChannelsRepository) {
    fun isFirstStart(): Observable<Boolean> {
        return repo.isDataSourceCreated()
    }

}