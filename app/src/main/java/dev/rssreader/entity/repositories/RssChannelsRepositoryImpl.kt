package dev.rssreader.entity.repositories

import android.util.Log
import dev.rssreader.domain.repositories.RssChannelsRepository
import javax.inject.Inject

class RssChannelsRepositoryImpl @Inject constructor() : RssChannelsRepository {
    private val mTAG = this::class.java.simpleName

    override fun addRssChannel(rsschannel: String) {
        Log.d(mTAG, "addRssChannel " + rsschannel)
    }
}