package dev.rssreader.entity.db.rsschannels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RssChannel {
    @PrimaryKey
    var id: Long = 0
    var address: String? = null
}