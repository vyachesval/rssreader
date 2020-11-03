package dev.rssreader.presentation.screen.list.news

import android.util.Log
import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.domain.usecase.GetRssChannelNews
import dev.rssreader.domain.usecase.GetRssChannelsList
import dev.rssreader.presentation.screen.list.ListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RssChannelNewsListViewModel @Inject constructor() : ListViewModel<RssChannelNewsData>() {

    @Inject
    lateinit var getRssChannelNews: GetRssChannelNews

    fun getRssChannelNews(urn: String) {
        Log.d("iszx", "getRssChannelNews " + urn)
        val disposable =
            getRssChannelNews.getRssChannelNews(urn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    list.value = it
                }
        compositeDisposable.add(disposable)
    }
}
