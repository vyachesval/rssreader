package dev.rssreader.domain.usecase

import org.junit.Assert.*
import org.junit.Test

class CheckRssChannelAddressStringTest {

    @Test
    fun isCorrectRssChannelAddressString() {
        assertTrue(CheckRssChannelAddressString.isCorrectRssChannelAddressString("http://mail.ru"))
        assertTrue(CheckRssChannelAddressString.isCorrectRssChannelAddressString("https://mail.ru/rss"))
        assertFalse(CheckRssChannelAddressString.isCorrectRssChannelAddressString("https://mail .ru/rss"))
    }
}