package dev.rssreader.entity.repositories.datasource

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.entity.db.AppDatabase
import dev.rssreader.entity.db.rsschannels.RssChannel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class LocalDataSource @Inject constructor(@ApplicationContext context: Context) {

    var isCreated = BehaviorSubject.create<Boolean>()
    val db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "rss_reader.db"
    )
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                isCreated.onNext(true)
            }
        })
        .fallbackToDestructiveMigration()
        .build()

    fun insert(rsschannel: String) : Completable {
        val rssChannelEntity = RssChannel(rsschannel)
        return db.rssChannelDao().insert(rssChannelEntity)
    }

    fun insertAll(rsschannels: List<RssChannel>) : Completable {
        return db.rssChannelDao().insertAll(rsschannels)
    }

    fun rssChannelList(): Observable<List<RssChannel>> {
        return db.rssChannelDao().getAll()
    }

    fun delete(rsschannelId: Int): Completable {
        return db.rssChannelDao().delete(rsschannelId)
    }

    fun update(rsschannel: RssChannel): Completable {
        return db.rssChannelDao().update(rsschannel.address, rsschannel.image, rsschannel.title)
    }

}