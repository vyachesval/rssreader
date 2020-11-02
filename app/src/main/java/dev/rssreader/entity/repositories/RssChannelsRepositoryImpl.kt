package dev.rssreader.entity.repositories

import android.util.Log
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.domain.repositories.RssChannelsRepository
import dev.rssreader.entity.repositories.datasource.LocalDataSource
import dev.rssreader.entity.repositories.datasource.RssChannelListMapper
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class RssChannelsRepositoryImpl @Inject constructor(dataSource: LocalDataSource) : RssChannelsRepository {
    private val mTAG = this::class.java.simpleName

    val localDataSource: LocalDataSource = dataSource
    val mapper: RssChannelListMapper = RssChannelListMapper()

    override fun addRssChannel(rsschannel: String) : Completable {
        Log.d(mTAG, "addRssChannel " + rsschannel)
        return localDataSource.insert(rsschannel)
    }

    override fun delRssChannel(rsschannelId: Int): Completable {
        return localDataSource.delete(rsschannelId)
    }

    override fun getRssChannelsList(): Observable<List<RssChannelData>> {
        return localDataSource.rssChannelList()
            .map(mapper::mapToData)
    }
}