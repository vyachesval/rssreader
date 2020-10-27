package dev.rssreader.entity.repositories

import android.util.Log
import dev.rssreader.domain.repositories.RssChannelsRepository
import dev.rssreader.entity.repositories.datasource.LocalDataSource
import javax.inject.Inject

class RssChannelsRepositoryImpl @Inject constructor(dataSource: LocalDataSource) : RssChannelsRepository {
    private val mTAG = this::class.java.simpleName

    val localDataSource: LocalDataSource = dataSource

    override fun addRssChannel(rsschannel: String) {
        Log.d(mTAG, "addRssChannel " + rsschannel)
        localDataSource.insert(rsschannel)
    }
}