package dev.rssreader.domain.entity

import java.io.Serializable

data class RssChannelData (
    val id: Int,
    val address: String,
    val icon: String
) : Serializable