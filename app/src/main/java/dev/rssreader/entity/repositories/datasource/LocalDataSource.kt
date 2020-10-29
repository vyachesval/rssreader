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
        AppDatabase::class.java, "rssreader"
    ).build()
    val mapper: RssChannelListMapper = RssChannelListMapper()

    fun insert(rsschannel: String) : Completable {
        val rssChannelEntity = RssChannel()
        rssChannelEntity.address = rsschannel
        return db.rssChannelDao().insert(rssChannelEntity)
    }

    fun rssChannelList(): Observable<List<String>> {
        return db.rssChannelDao().getAll()
            .map(mapper::mapToStringList)
    }

}