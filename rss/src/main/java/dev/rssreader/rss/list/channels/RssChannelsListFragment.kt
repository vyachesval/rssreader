package dev.rssreader.rss.list.channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import dev.rssreader.rss.R
import dev.rssreader.rss.R2
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.rss.dialog.addchannel.AddRssChannelDialogFragment
import dev.rssreader.rss.dialog.delchannel.DelRssChannelDialogFragment
import dev.rssreader.rss.list.ItemClickListener
import dev.rssreader.rss.list.ListFragment

@AndroidEntryPoint
class RssChannelsListFragment : ListFragment() {

    @BindView(R2.id.fab)
    lateinit var fabView: FloatingActionButton

    private val viewModel: RssChannelListViewModel by viewModels()

    private val adapter = RssChannelListAdapter(
        object : ItemClickListener<RssChannelData> {
            override fun onClick(position: Int, item: RssChannelData) {
                findNavController().navigate(
                    R.id.action_RssChannelsList_to_RssChannelNewsList,
                    bundleOf("rsschannelUrl" to item.address)
                )
            }

            override fun onLongClick(position: Int, item: RssChannelData): Boolean {
                activity?.let {
                    val args = Bundle()
                    args.putSerializable("rsschannelitem", item)
                    val delRssChannelDialog = DelRssChannelDialogFragment()
                    delRssChannelDialog.arguments = args
                    val manager = it.supportFragmentManager
                    delRssChannelDialog.show(manager, "DelRssChannel")
                } ?: throw IllegalStateException("Activity cannot be null")
                return true
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

        fabView.setOnClickListener {
            activity?.let {
                val addChannelDialog = AddRssChannelDialogFragment()
                val manager = it.supportFragmentManager
                addChannelDialog.show(manager, "AddRssChannel")
            } ?: throw IllegalStateException("Activity cannot be null")
        }

        rsschannelsList.layoutManager = LinearLayoutManager(view.context)

        rsschannelsList.adapter = adapter

        val listObserver = Observer<List<RssChannelData>> { list ->
            adapter.setNewList(list)
        }

        viewModel.list.observe(viewLifecycleOwner, listObserver)
    }
}