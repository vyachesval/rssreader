package dev.rssreader.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.rssreader.RssReaderApplication
import io.reactivex.disposables.CompositeDisposable
import toothpick.Toothpick
import javax.inject.Inject

open class ListViewModel <T> @Inject constructor() : ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    open val list: MutableLiveData<List<T>> by lazy {
        MutableLiveData<List<T>>()
    }

    init {
        Toothpick.inject(this,  Toothpick.openScope(RssReaderApplication.instance))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}