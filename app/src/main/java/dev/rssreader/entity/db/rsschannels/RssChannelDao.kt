package dev.rssreader.entity.db.rsschannels

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
abstract class RssChannelDao {
    @Query("SELECT * FROM rsschannel")
    abstract fun getAll(): Observable<List<RssChannel>>

    @Query("SELECT * FROM rsschannel WHERE id = :id")
    abstract fun getById(id: Long): Maybe<RssChannel>

    @Insert
    abstract fun insert(channel: RssChannel): Completable

    @Update
    abstract fun update(channel: RssChannel) : Completable

    @Delete
    abstract fun delete(channel: RssChannel) : Completable
}