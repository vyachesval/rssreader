package dev.rssreader.presentation.screen.addchannel

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Single

class AddChannelViewModel : ViewModel() {

    val URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$"

    fun isCorrectChannel(channel: String): Single<Boolean> {
        return Observable.just(channel)
            .filter { it.matches(Regex(URL_REGEX)) }
            .count()
            .map { it > 0 }
    }

}