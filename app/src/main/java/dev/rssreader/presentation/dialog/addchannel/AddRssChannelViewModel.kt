package dev.rssreader.presentation.dialog.addchannel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rssreader.domain.usecase.AddRssChannel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AddRssChannelViewModel @Inject constructor(private val addRssChannel: AddRssChannel) :
    ViewModel() {

    private val mTAG = this::class.java.simpleName

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun addRssChannel(rsschannel: String) {
        val disposable = addRssChannel.addRssChannel(rsschannel)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}