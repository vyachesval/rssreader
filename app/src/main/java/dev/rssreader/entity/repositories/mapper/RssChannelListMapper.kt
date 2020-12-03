package dev.rssreader.entity.repositories.mapper

import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.entity.db.rsschannels.RssChannel
import java.util.stream.Collectors

class RssChannelListMapper {
        fun mapToData(list: List<RssChannel>): List<RssChannelData> {
            return list.stream()
                .map { RssChannelData(it.id.toInt(), it.address) }
                .collect(Collectors.toList())
                .requireNoNulls()
        }

        fun mapToEntity(list: Array<String>): List<RssChannel> {
            return list.toList().stream()
                .map { RssChannel(0, it) }
                .collect(Collectors.toList())
                .requireNoNulls()
        }
}