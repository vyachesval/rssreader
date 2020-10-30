package dev.rssreader.entity.db.rsschannels

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = arrayOf(Index(value = ["address"], unique = true) ))
data class RssChannel (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val address: String
) {
    constructor(address: String) : this(0, address)
}
