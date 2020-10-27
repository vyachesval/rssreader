package dev.rssreader.presentation.screen.addchannel

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.rssreader.domain.usecase.AddRssChannel
import dev.rssreader.domain.usecase.CheckRssChannelAddressString
import javax.inject.Inject

class AddRssChannelViewModel @Inject constructor(private val addRssChannel: AddRssChannel) :
    ViewModel() {

    private val mTAG = this::class.java.simpleName

    fun addRssChannel(rsschannel: String) {

            return CheckRssChannelAddressString.isCorrectRssChannelAddressString(rsschannel)
            .map { if(it) addRssChannel.addRssChannel(rsschannel) }
            .subscribe { value -> Log.d(mTAG, "isCorrectRssChannelAddressString return $value")  }
            .dispose()

    }
}