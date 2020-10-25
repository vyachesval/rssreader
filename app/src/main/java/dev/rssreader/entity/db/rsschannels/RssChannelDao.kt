package dev.rssreader.entity.db.rsschannels

import androidx.room.*

@Dao
abstract class RssChannelDao {
    @Query("SELECT * FROM rsschannel")
    abstract fun getAll(): List<RssChannel>?

    @Query("SELECT * FROM rsschannel WHERE id = :id")
    abstract fun getById(id: Long): RssChannel?

    @Insert
    abstract fun insert(channel: RssChannel)

    @Update
    abstract fun update(channel: RssChannel)

    @Delete
    abstract fun delete(channel: RssChannel)
}