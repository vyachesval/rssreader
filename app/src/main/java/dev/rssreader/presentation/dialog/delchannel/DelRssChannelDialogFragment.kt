package dev.rssreader.presentation.dialog.delchannel

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import dev.rssreader.R
import dev.rssreader.RssReaderApplication
import dev.rssreader.di.ViewModelFactory
import dev.rssreader.domain.entity.RssChannelData
import toothpick.Toothpick
import javax.inject.Inject

class DelRssChannelDialogFragment : DialogFragment() {

    private lateinit var viewModel: DelRssChannelViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @BindView(R.id.del_rsschannel_text_view)
    lateinit var testView: TextView

    @Throws(IllegalStateException::class)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Toothpick.inject(this, Toothpick.openScope(RssReaderApplication.instance))
        val delRssChannelView =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog_del_rss_channel, null)
        ButterKnife.bind(this, delRssChannelView)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(DelRssChannelViewModel::class.java)

        return activity?.let {
            val item = requireArguments().getSerializable("rsschannelitem") as RssChannelData
            testView.text = getString(R.string.del_rsschannel_hint, item.address)
            val builder = AlertDialog.Builder(it)
                .setTitle( R.string.del_rsschannel_title)
                .setIcon(R.drawable.ic_action_name)
                .setPositiveButton(R.string.del) { _, _ ->
                    viewModel.delRssChannel(item)
                }
                .setView(delRssChannelView)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}