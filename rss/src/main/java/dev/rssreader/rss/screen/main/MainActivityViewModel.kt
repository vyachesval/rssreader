package dev.rssreader.rss.screen.main


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.rssreader.rss.R
import dev.rssreader.domain.usecase.AddRssChannelsList
import dev.rssreader.domain.usecase.IsFirstStart
import dev.rssreader.entity.network.InternetConnectionListener
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val isFirstStart: IsFirstStart,
    private val addRssChannelsList: AddRssChannelsList,
    private val internetConnectionListener: InternetConnectionListener,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val supposeInternetLost: MutableLiveData<Unit> by lazy {
        MutableLiveData<Unit>()
    }

    fun initDefaultList() {
        compositeDisposable.add(
            isFirstStart.isFirstStart()
                .switchMapCompletable {
                    if(it) {
                        addRssChannelsList.addRssChannelsList(context.resources.getStringArray(R.array.defaultChannels))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                    } else {
                        Completable.complete()
                    }
                }.subscribe()
        )
        compositeDisposable.add(
            internetConnectionListener.subject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    supposeInternetLost.value = it
                })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
