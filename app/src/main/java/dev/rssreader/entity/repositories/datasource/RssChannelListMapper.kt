package dev.rssreader.entity.repositories.datasource

import dev.rssreader.entity.db.rsschannels.RssChannel
import java.util.stream.Collectors

class RssChannelListMapper {
        fun mapToStringList(list: List<RssChannel>): List<String> {
            return list.stream()
                .map { it.address }
                .collect(Collectors.toList())
                .requireNoNulls()
        }

}