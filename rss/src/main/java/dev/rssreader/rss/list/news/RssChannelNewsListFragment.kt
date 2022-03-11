package dev.rssreader.rss.list.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import dagger.hilt.android.AndroidEntryPoint
import dev.rssreader.rss.R
import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.rss.list.ItemClickListener
import dev.rssreader.rss.list.ListFragment
import kotlinx.android.synthetic.main.add_channel_button.view.*

@AndroidEntryPoint
class RssChannelNewsListFragment : ListFragment() {

    private val viewModel: RssChannelNewsListViewModel by viewModels()

    private val adapter = RssChannelNewsListAdapter(
        object : ItemClickListener<RssChannelNewsData> {
            override fun onClick(position: Int, item: RssChannelNewsData) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.link)))
            }

            override fun onLongClick(position: Int, item: RssChannelNewsData): Boolean {
                return false
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        url?.let {
            viewModel.getRssChannelNews(it)
        }
    }
}