package dev.rssreader.presentation.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import dev.rssreader.R
import dev.rssreader.RssReaderApplication
import dev.rssreader.di.ViewModelFactory
import dev.rssreader.domain.usecase.GetRssChannelsList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import toothpick.Toothpick
import javax.inject.Inject

class RssChannelsListFragment : Fragment() {

    @BindView(R.id.rsschannels_list)
    lateinit var rsschannelsList: RecyclerView
    private lateinit var viewModel: RssChannelListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var getRssChannelsList: GetRssChannelsList

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        Toothpick.inject(this,  Toothpick.openScope(RssReaderApplication.instance))
        viewModel = ViewModelProvider(this, viewModelFactory).get(RssChannelListViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_rsschannels_list, container, false)
        ButterKnife.bind(this, view)
        rsschannelsList.layoutManager = LinearLayoutManager(view.context)

        val adapter = RssChannelListAdapter()
        rsschannelsList.adapter = adapter

        val disposable =
            getRssChannelsList.getRssChannelsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                adapter.setList(it)
                compositeDisposable.clear()
            }

        compositeDisposable.add(disposable)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}