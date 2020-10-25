package dev.rssreader.presentation.screen.addchannel

import androidx.lifecycle.ViewModel
import dev.rssreader.domain.usecase.CheckRssChannelAddressString
import io.reactivex.Observable

class AddRssChannelViewModel : ViewModel() {
    fun isCorrectRssChannel(channel: String): Observable<Boolean> {
        return CheckRssChannelAddressString.isCorrectRssChannelAddressString(channel)
    }
}