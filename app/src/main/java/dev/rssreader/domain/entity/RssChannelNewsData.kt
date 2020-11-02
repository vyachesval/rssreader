package dev.rssreader.domain.entity

import java.time.LocalDateTime

data class RssChannelNewsData (
    val id: Int,
    val title: String,
    val date: LocalDateTime,
    val link: String,
    val description: String
)