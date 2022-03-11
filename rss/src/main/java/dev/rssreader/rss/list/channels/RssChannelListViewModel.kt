package dev.rssreader.rss.list.channels

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.domain.usecase.GetRssChannelsList
import dev.rssreader.domain.usecase.ObserveRssChannelList
import dev.rssreader.rss.list.ListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RssChannelListViewModel @Inject constructor(
    getRssChannelsList: GetRssChannelsList,
    observeRssChannelList: ObserveRssChannelList
) : ListViewModel<RssChannelData>() {

    init {
        compositeDisposable.add(
            getRssChannelsList.getRssChannelsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    list.value = it
                }
        )
        compositeDisposable.add(
            observeRssChannelList.observeRssChannelList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        )
    }
}
