package dev.rssreader.presentation.list.channels

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.domain.usecase.GetRssChannelsList
import dev.rssreader.presentation.list.ListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RssChannelListViewModel @Inject constructor(getRssChannelsList: GetRssChannelsList) : ListViewModel<RssChannelData>() {

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
