package dev.rssreader.rss.dialog.delchannel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.domain.usecase.DelRssChannel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DelRssChannelViewModel @Inject constructor(private val delRssChannel: DelRssChannel) :
    ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun delRssChannel(rsschannel: RssChannelData) {
        val disposable =
            delRssChannel.delRssChannel(rsschannel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}