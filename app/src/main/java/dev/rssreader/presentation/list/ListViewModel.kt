package dev.rssreader.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class ListViewModel <T> @Inject constructor() : ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    open val list: MutableLiveData<List<T>> by lazy {
        MutableLiveData<List<T>>()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}