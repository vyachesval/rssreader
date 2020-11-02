package dev.rssreader.presentation.screen.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.rssreader.RssReaderApplication
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.domain.usecase.DelRssChannel
import dev.rssreader.domain.usecase.GetRssChannelsList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import toothpick.Toothpick
import javax.inject.Inject

class RssChannelListViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var getRssChannelsList: GetRssChannelsList
    @Inject
    lateinit var delRssChannelsList: DelRssChannel

    val list: MutableLiveData<List<RssChannelData>> by lazy {
        MutableLiveData<List<RssChannelData>>()
    }

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        Toothpick.inject(this,  Toothpick.openScope(RssReaderApplication.instance))
        val disposable =
            getRssChannelsList.getRssChannelsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    list.value = it
                }
        compositeDisposable.add(disposable)
    }


    fun onClick(rsschannel: RssChannelData) {

    }

    fun onLongClick(rsschannel: RssChannelData) {
        val disposable =
            delRssChannelsList.delRssChannel(rsschannel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        compositeDisposable.add(disposable)
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}
