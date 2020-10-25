package dev.rssreader.presentation.screen.addchannel

import org.junit.Assert.*
import org.junit.Test

class AddRssChannelViewModelTest {

    @Test
    fun isCorrectRssChannel() {
        val vm = AddRssChannelViewModel()
        assertTrue(vm.isCorrectRssChannel("http://mail.ru").blockingFirst())
        assertTrue(vm.isCorrectRssChannel("https://mail.ru/rss").blockingFirst())
        assertFalse(vm.isCorrectRssChannel("https://mail .ru/rss").blockingFirst())
    }
}