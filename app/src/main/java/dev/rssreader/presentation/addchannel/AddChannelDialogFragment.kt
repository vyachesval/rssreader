package dev.rssreader.presentation.addchannel

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import dev.rssreader.R
import dev.rssreader.presentation.main.MainActivityViewModel

class AddChannelDialogFragment : DialogFragment() {

    private val mTAG = this::class.java.simpleName
    private lateinit var viewModel: AddChannelViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProvider.NewInstanceFactory().create(AddChannelViewModel::class.java)

        return activity?.let {
            val dialogView: View = LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_channel, null)
            val input_channel = dialogView.findViewById<EditText>(R.id.input_channel)
            val builder = AlertDialog.Builder(it)
                .setTitle(R.string.add_channel_title)
                .setIcon(R.drawable.ic_action_name)
                .setPositiveButton(R.string.add) { _, _ ->
                    Log.d(mTAG, input_channel.text.toString())
                }
            builder.setView(dialogView)
            builder.create()
        } ?: throw IllegalStateException("No activity")
    }

}
