package dev.rssreader.domain.repositories

import dev.rssreader.domain.entity.RssChannelNewsData
import io.reactivex.Observable

interface RssChannelNewsRepository {
    fun getRssChannelNews(rsschannelUrl: String): Observable<List<RssChannelNewsData>>
}