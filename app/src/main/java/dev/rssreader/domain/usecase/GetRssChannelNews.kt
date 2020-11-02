package dev.rssreader.domain.usecase

import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.domain.repositories.RssChannelNewsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRssChannelNews @Inject constructor(val repo: RssChannelNewsRepository){
    fun getRssChannelNews(rsschannelUrl: String): Observable<List<RssChannelNewsData>> {
        return repo.getRssChannelNews(rsschannelUrl)
    }
}