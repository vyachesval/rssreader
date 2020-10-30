package dev.rssreader.presentation.screen.addchannel

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import dev.rssreader.R
import dev.rssreader.RssReaderApplication
import dev.rssreader.di.ViewModelFactory
import dev.rssreader.domain.usecase.CheckRssChannelAddressString
import toothpick.Toothpick
import javax.inject.Inject

class AddRssChannelDialogFragment : DialogFragment() {

    private val mTAG = this::class.java.simpleName
    private lateinit var viewModel: AddRssChannelViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @BindView(R.id.input_rsschannel)
    lateinit var inputRsschannelView: EditText

    @Throws(IllegalStateException::class)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        Toothpick.inject(this, Toothpick.openScope(RssReaderApplication.instance))
        val addRssChannelView =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_rsschannel, null)
        ButterKnife.bind(this, addRssChannelView)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(AddRssChannelViewModel::class.java)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setTitle(R.string.add_rsschannel_title)
                .setIcon(R.drawable.ic_action_name)
                .setPositiveButton(R.string.add, null)
                .setView(addRssChannelView)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }



    override fun onStart() {
        super.onStart()
        if(dialog is AlertDialog) {
            val button = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
            button?.setOnClickListener {
                Log.d(mTAG, "onClick " + inputRsschannelView.text.toString())
                if(CheckRssChannelAddressString.isCorrectRssChannelAddressString(inputRsschannelView.text.toString())) {
                    viewModel.addRssChannel(inputRsschannelView.text.toString())
                    dismiss()
                }
                else {
                    inputRsschannelView.setError(getString(R.string.enter_address_error))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}
