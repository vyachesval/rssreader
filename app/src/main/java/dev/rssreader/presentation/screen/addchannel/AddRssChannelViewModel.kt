package dev.rssreader.presentation.screen.addchannel

import androidx.lifecycle.ViewModel
import dev.rssreader.domain.usecase.AddRssChannel
import dev.rssreader.domain.usecase.CheckRssChannelAddressString
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddRssChannelViewModel @Inject constructor(private val addRssChannel: AddRssChannel) :
    ViewModel() {

    private val mTAG = this::class.java.simpleName

    fun addRssChannel(rsschannel: String) {
        val disposables = CompositeDisposable()
        CheckRssChannelAddressString.isCorrectRssChannelAddressString(rsschannel)
            .map { if(it) addRssChannel.addRssChannel(rsschannel) }
            .subscribeOn(Schedulers.io())
            .subscribe()
        disposables.dispose()
    }
}