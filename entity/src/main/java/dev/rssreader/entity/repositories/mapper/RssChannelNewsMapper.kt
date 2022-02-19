package dev.rssreader.entity.repositories.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.entity.network.Rss
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

class RssChannelNewsMapper {
    @RequiresApi(Build.VERSION_CODES.O)
    fun mapToData(rss: Rss): List<RssChannelNewsData> {
        return rss.channel.items.stream()
            .map {
                RssChannelNewsData(
                    it.title
                    , LocalDateTime.parse(it.pubDate
                        , DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z"))
                    , it.link
                    , it.description
                ) }
            .collect(Collectors.toList())
            .requireNoNulls()
    }
}