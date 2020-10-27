package dev.rssreader.domain.usecase

import org.junit.Assert.*
import org.junit.Test

class CheckRssChannelAddressStringTest {

    @Test
    fun isCorrectRssChannelAddressString() {
        assertTrue(CheckRssChannelAddressString.isCorrectRssChannelAddressString("http://mail.ru").blockingGet())
        assertTrue(CheckRssChannelAddressString.isCorrectRssChannelAddressString("https://mail.ru/rss").blockingGet())
        assertFalse(CheckRssChannelAddressString.isCorrectRssChannelAddressString("https://mail .ru/rss").blockingGet())
    }
}