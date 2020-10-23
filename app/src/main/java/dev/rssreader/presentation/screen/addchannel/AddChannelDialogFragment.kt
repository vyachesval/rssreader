package dev.rssreader.presentation.screen.addchannel

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import dev.rssreader.R

class AddChannelDialogFragment : DialogFragment() {

    private val mTAG = this::class.java.simpleName
    private lateinit var viewModel: AddChannelViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProvider.NewInstanceFactory().create(AddChannelViewModel::class.java)

        return activity?.let {
            var addChannelView = LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_channel, null)
            val input_channel = addChannelView.findViewById<EditText>(R.id.input_channel)
            val builder = AlertDialog.Builder(it)
                .setTitle(R.string.add_channel_title)
                .setIcon(R.drawable.ic_action_name)
                .setPositiveButton(R.string.add) { _, _ ->
                    Log.d(mTAG, input_channel.text.toString())

                    viewModel.isCorrectChannel(input_channel.text.toString())
                        .subscribe { it ->
                            Log.d(mTAG, "isCorrectChannel return " + it)
                        }
                }
            builder.setView(addChannelView)
            builder.create()
        } ?: throw IllegalStateException("No activity")
    }
}
