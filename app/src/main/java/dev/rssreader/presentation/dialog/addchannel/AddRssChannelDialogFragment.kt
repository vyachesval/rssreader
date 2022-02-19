package dev.rssreader.presentation.dialog.addchannel

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import butterknife.BindView
import butterknife.ButterKnife
import dagger.hilt.android.AndroidEntryPoint
import dev.rssreader.R
import dev.rssreader.domain.usecase.CheckRssChannelAddressString

@AndroidEntryPoint
class AddRssChannelDialogFragment : DialogFragment() {

    private val mTAG = this::class.java.simpleName
    private val viewModel: AddRssChannelViewModel by viewModels()

    @BindView(R.id.input_rsschannel)
    lateinit var inputRsschannelView: EditText

    @Throws(IllegalStateException::class)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val addRssChannelView =
            LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_rsschannel, null)
        ButterKnife.bind(this, addRssChannelView)
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
}
