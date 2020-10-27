package dev.rssreader.entity.repositories.datasource

import android.content.Context
import androidx.room.Room
import dev.rssreader.entity.db.AppDatabase
import dev.rssreader.entity.db.rsschannels.RssChannel
import javax.inject.Inject

class LocalDataSource @Inject constructor(context: Context) {
    val db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "rssreader"
    ).build()

    fun insert(rsschannel: String) {
        val rssChannelEntity = RssChannel()
        rssChannelEntity.address = rsschannel
        db.rssChannelDao().insert(rssChannelEntity)
    }
}