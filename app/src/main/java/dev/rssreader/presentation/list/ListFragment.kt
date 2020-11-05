package dev.rssreader.presentation.list

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import dev.rssreader.R
import dev.rssreader.RssReaderApplication
import dev.rssreader.di.ViewModelFactory
import toothpick.Toothpick
import javax.inject.Inject

open class ListFragment : Fragment() {
    @BindView(R.id.viewitemlist)
    protected lateinit var rsschannelsList: RecyclerView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    init {
        Toothpick.inject(this,  Toothpick.openScope(RssReaderApplication.instance))
    }

    fun <VMT : ListViewModel<TD>,TD> provideViewModel(vmClass: Class<VMT>): VMT {
        return ViewModelProvider(this, viewModelFactory).get(vmClass)
    }
}