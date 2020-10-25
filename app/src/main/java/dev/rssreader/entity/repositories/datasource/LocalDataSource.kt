package dev.rssreader.entity.repositories.datasource

import android.content.Context
import androidx.room.Room
import dev.rssreader.entity.db.AppDatabase

class LocalDataSource(context: Context) {
    val db: AppDatabase

    init {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "rssreader"
        ).build()
    }
}