package dev.rssreader.entity.repositories.datasource

import android.content.Context
import androidx.room.Room
import dev.rssreader.entity.db.AppDatabase
import dev.rssreader.entity.db.rsschannels.RssChannel
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSource @Inject constructor(context: Context) {
    val db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "rss_reader.db"
    ).build()

    fun insert(rsschannel: String) : Completable {
        val rssChannelEntity = RssChannel(rsschannel)
        return db.rssChannelDao().insert(rssChannelEntity)
    }

    fun rssChannelList(): Observable<List<RssChannel>> {
        return db.rssChannelDao().getAll()
    }

    fun delete(rsschannelId: Int): Completable {
        return db.rssChannelDao().delete(rsschannelId)
    }

}