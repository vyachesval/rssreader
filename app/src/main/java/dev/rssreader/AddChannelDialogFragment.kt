package dev.rssreader

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment


class AddChannelDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //setStyle(STYLE_NORMAL, dev.rssreader.R.style.CustomDialog)
        return activity?.let {
            val dialogView: View = LayoutInflater.from(context).inflate(dev.rssreader.R.layout.fragment_dialog_add_channel, null)
            val builder = AlertDialog.Builder(it)
                .setTitle(R.string.add_channel_title)
                .setIcon(R.drawable.ic_action_name)
                .setPositiveButton(R.string.add) {
                        dialog, id -> dialog.cancel()
                }
            builder.setView(dialogView)
            builder.create()
        } ?: throw IllegalStateException("No activity")
    }

}
