package dev.rssreader.entity.repositories

import android.util.Log
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.domain.repositories.RssChannelsRepository
import dev.rssreader.entity.repositories.datasource.LocalDataSource
import dev.rssreader.entity.repositories.mapper.RssChannelListMapper
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class RssChannelsRepositoryImpl @Inject constructor(val localDataSource: LocalDataSource) : RssChannelsRepository {

    private val mTAG = this::class.java.simpleName

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

    override fun addRssChannelsList(list: Array<String>): Completable {
        return Observable.fromArray(list)
            .map(mapper::mapToEntity)
            .flatMapCompletable { localDataSource.insertAll(it)}
    }

    override fun isDataSourceCreated(): Observable<Boolean> {
        return localDataSource.isCreated
    }
}
