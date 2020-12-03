package dev.rssreader.presentation.list.channels

import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.domain.usecase.DelRssChannel
import dev.rssreader.domain.usecase.GetRssChannelNews
import dev.rssreader.domain.usecase.GetRssChannelsList
import dev.rssreader.presentation.list.ListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RssChannelListViewModel @Inject constructor() : ListViewModel<RssChannelData>() {

    @Inject
    lateinit var getRssChannelsList: GetRssChannelsList

    init {
        val disposable =
            getRssChannelsList.getRssChannelsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    list.value = it
                }
        compositeDisposable.add(disposable)
    }
}
