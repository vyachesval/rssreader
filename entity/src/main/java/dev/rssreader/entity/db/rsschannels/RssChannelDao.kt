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

    @Query("select last_insert_rowid()")
    abstract fun getLastId(): Maybe<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(channel: RssChannel): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAll(channels: List<RssChannel>): Completable

    @Update
    abstract fun update(channel: RssChannel) : Completable

    @Query("DELETE FROM rsschannel WHERE id = :rsschannel_id")
    abstract fun delete(rsschannel_id: Int) : Completable
}