package dev.rssreader.presentation.screen.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import dev.rssreader.R
import dev.rssreader.RssReaderApplication
import dev.rssreader.di.ViewModelFactory
import dev.rssreader.domain.entity.RssChannelData
import toothpick.Toothpick
import javax.inject.Inject

class RssChannelsListFragment : Fragment() {

    @BindView(R.id.rsschannels_list)
    lateinit var rsschannelsList: RecyclerView
    private lateinit var viewModel: RssChannelListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        Toothpick.inject(this,  Toothpick.openScope(RssReaderApplication.instance))
        viewModel = ViewModelProvider(this, viewModelFactory).get(RssChannelListViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_rsschannels_list, container, false)
        ButterKnife.bind(this, view)
        rsschannelsList.layoutManager = LinearLayoutManager(view.context)
        val adapter = RssChannelListAdapter(
            object: ItemClickListener<RssChannelData>() {
                override fun onClick(position: Int, item: RssChannelData) {
                    viewModel.onClick(item)
                }

                override fun onLongClick(position: Int, item: RssChannelData): Boolean {
                    showConfirmDialog(item)
                    return true
                }
            })

        rsschannelsList.adapter = adapter

        val listObserver = Observer<List<RssChannelData>> { list ->
            updateList(list)
        }

        viewModel.list.observe(viewLifecycleOwner, listObserver)

        return view
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

    fun updateList(list: List<RssChannelData>) {
        (rsschannelsList.adapter as RssChannelListAdapter).setList(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}