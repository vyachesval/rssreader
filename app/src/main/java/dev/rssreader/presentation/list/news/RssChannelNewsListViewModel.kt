package dev.rssreader.presentation.list.news

import android.util.Log
import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.domain.usecase.GetRssChannelNews
import dev.rssreader.presentation.list.ListViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RssChannelNewsListViewModel @Inject constructor() : ListViewModel<RssChannelNewsData>() {

    @Inject
    lateinit var getRssChannelNews: GetRssChannelNews

    fun getRssChannelNews(urn: String) {
        //val disposable =
            getRssChannelNews.getRssChannelNews(urn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ( object : Observer<List<RssChannelNewsData>> {
                    override fun onNext(it: List<RssChannelNewsData>) {
                        Log.d("iszx", "getRssChannelNews next " + it)
                        list.value = it
                    }

                    override fun onError(e: Throwable) {
                        Log.d("iszx", "getRssChannelNews error " + e)
                    }

                    override fun onComplete() {
                        Log.d("iszx", "getRssChannelNews complete")
                    }
                    override fun onSubscribe(d: Disposable) {
                        Log.d("iszx", "getRssChannelNews subscribe " + d)
                    }
                }

                )


        //compositeDisposable.add(disposable)
    }
}
