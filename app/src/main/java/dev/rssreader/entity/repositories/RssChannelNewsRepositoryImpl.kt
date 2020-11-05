package dev.rssreader.entity.repositories

import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.domain.repositories.RssChannelNewsRepository
import dev.rssreader.entity.repositories.datasource.RemoteDataSource
import dev.rssreader.entity.repositories.mapper.RssChannelNewsMapper
import io.reactivex.Observable
import javax.inject.Inject

class RssChannelNewsRepositoryImpl @Inject constructor(dataSource: RemoteDataSource) : RssChannelNewsRepository{

    val remoteDataSource: RemoteDataSource = dataSource
    val mapper: RssChannelNewsMapper = RssChannelNewsMapper()

    override fun getRssChannelNews(rsschannelUrl: String): Observable<List<RssChannelNewsData>> {
        return remoteDataSource.getRssChannelNews(rsschannelUrl)
            .map(mapper::mapToData)
    }
}