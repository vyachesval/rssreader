package dev.rssreader.presentation.screen.list.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import dev.rssreader.R
import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.presentation.screen.list.ItemClickListener
import dev.rssreader.presentation.screen.list.ListFragment
import kotlinx.android.synthetic.main.add_channel_button.view.*

class RssChannelNewsListFragment : ListFragment() {

    private lateinit var viewModel: RssChannelNewsListViewModel

    private val adapter = RssChannelNewsListAdapter(
        object: ItemClickListener<RssChannelNewsData> {
            override fun onClick(position: Int, item: RssChannelNewsData) {
                Log.d("iszx", "RssChannelNewsListFragment opening news link" + item.link)
                viewModel.getRssChannelNews(item.link)
            }
            override fun onLongClick(position: Int, item: RssChannelNewsData): Boolean {
                return false
            }
        })

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = provideViewModel(RssChannelNewsListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        view.fab.hide()

        rsschannelsList.layoutManager = LinearLayoutManager(view.context)

        rsschannelsList.adapter = adapter

        val listObserver = Observer<List<RssChannelNewsData>> { list ->
            adapter.setNewList(list)
        }

        viewModel.list.observe(viewLifecycleOwner, listObserver)

        val url = arguments?.getString("rsschannelUrl")
        Log.d("iszx", "RssChannelNewsListFragment get " + url)
        url?.let {
            viewModel.getRssChannelNews(it)
        }
    }
}