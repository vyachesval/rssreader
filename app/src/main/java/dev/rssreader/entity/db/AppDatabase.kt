package dev.rssreader.entity.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.rssreader.entity.db.rsschannels.RssChannel

@Database(
    entities = [RssChannel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

}