package dev.rssreader.presentation.list.channels

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.rssreader.R
import dev.rssreader.domain.entity.RssChannelData
import dev.rssreader.presentation.dialog.addchannel.AddRssChannelDialogFragment
import dev.rssreader.presentation.dialog.delchannel.DelRssChannelDialogFragment
import dev.rssreader.presentation.list.ItemClickListener
import dev.rssreader.presentation.list.ListFragment

class RssChannelsListFragment : ListFragment() {

    @BindView(R.id.fab)
    lateinit var fabView: FloatingActionButton

    private lateinit var viewModel: RssChannelListViewModel

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

        viewModel = provideViewModel(RssChannelListViewModel::class.java)
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