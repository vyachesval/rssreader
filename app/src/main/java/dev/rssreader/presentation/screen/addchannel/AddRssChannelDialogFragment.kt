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

class AddRssChannelDialogFragment : DialogFragment() {

    private val mTAG = this::class.java.simpleName
    private lateinit var viewModel: AddRssChannelViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProvider.NewInstanceFactory().create(AddRssChannelViewModel::class.java)

        return activity?.let {
            val addRssChannelView = LayoutInflater.from(context).inflate(R.layout.fragment_dialog_add_rsschannel, null)
            val input_rsschannel = addRssChannelView.findViewById<EditText>(R.id.input_rsschannel)
            val builder = AlertDialog.Builder(it)
                .setTitle(R.string.add_rsschannel_title)
                .setIcon(R.drawable.ic_action_name)
                .setPositiveButton(R.string.add) { _, _ ->
                    Log.d(mTAG, input_rsschannel.text.toString())

                    viewModel.isCorrectRssChannel(input_rsschannel.text.toString())
                        .subscribe { it ->
                            Log.d(mTAG, "isCorrectChannel return " + it)
                        }
                }
            builder.setView(addRssChannelView)
            builder.create()
        } ?: throw IllegalStateException("No activity")
    }
}
