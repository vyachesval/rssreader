package dev.rssreader.entity.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name="rss", strict = false)
data class Rss (
    @field:Element(name="channel")
    @param:Element(name="channel")
    val channel: Channel
)

@Root(name="channel", strict = false)
data class Channel (
    @field:ElementList(inline=true)
    @param:ElementList(inline=true)
    val items: List<Item>
)

@Root(name="item", strict = false)
data class Item (
    @field:Element(name="title")
    @param:Element(name="title")
    val title: String,

    @field:Element(name="link")
    @param:Element(name="link")
    val link: String,

    @field:Element(name="description")
    @param:Element(name="description")
    val description: String,

    @field:Element(name="pubDate")
    @param:Element(name="pubDate")
    val pubDate: String
)
