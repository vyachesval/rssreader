package dev.rssreader.entity.repositories.datasource

import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.entity.db.rsschannels.RssChannel
import java.util.stream.Collectors

class RssChannelListMapper {
        fun mapToData(list: List<RssChannel>): List<RssChannelData> {
            return list.stream()
                .map { RssChannelData(it.id, it.address) }
                .collect(Collectors.toList())
                .requireNoNulls()
        }
}