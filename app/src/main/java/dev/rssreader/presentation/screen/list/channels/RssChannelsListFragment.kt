package dev.rssreader.presentation.screen.list.channels

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
import dev.rssreader.presentation.screen.addchannel.AddRssChannelDialogFragment
import dev.rssreader.presentation.screen.list.ItemClickListener
import dev.rssreader.presentation.screen.list.ListFragment


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
                showConfirmDialog(item)
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

    private fun showConfirmDialog(item: RssChannelData) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(R.string.del_rsschannel_title)
            .setPositiveButton(R.string.del) { _, _ ->
                viewModel.onLongClick(item)
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }
}