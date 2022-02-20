package dev.rssreader.presentation.list.news

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.domain.usecase.GetRssChannelNews
import dev.rssreader.presentation.list.ListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RssChannelNewsListViewModel @Inject constructor(
    private val getRssChannelNews: GetRssChannelNews
) : ListViewModel<RssChannelNewsData>() {


    fun getRssChannelNews(urn: String) {
        val disposable =
            getRssChannelNews.getRssChannelNews(urn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list.value = it }

        compositeDisposable.add(disposable)
    }
}
