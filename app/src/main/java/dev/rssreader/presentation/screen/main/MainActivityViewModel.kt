package dev.rssreader.presentation.screen.main


import android.content.Context
import androidx.lifecycle.ViewModel
import dev.rssreader.R
import dev.rssreader.domain.usecase.AddRssChannelsList
import dev.rssreader.domain.usecase.IsFirstStart
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val isFirstStart: IsFirstStart, private val addRssChannelsList: AddRssChannelsList, private val context: Context) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

     fun initDefaultList() {
        if(isFirstStart.isFirstStart()) {
            val disposable =
                addRssChannelsList.addRssChannelsList(context.resources.getStringArray(R.array.defaultChannels))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            compositeDisposable.add(disposable)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
