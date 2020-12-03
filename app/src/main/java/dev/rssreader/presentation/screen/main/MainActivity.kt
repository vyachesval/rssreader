package dev.rssreader.presentation.screen.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import dev.rssreader.R
import dev.rssreader.RssReaderApplication
import dev.rssreader.di.ViewModelFactory
import dev.rssreader.entity.network.InternetConnectionListener
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : AppCompatActivity(), InternetConnectionListener {

    private lateinit var viewModel: MainActivityViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, Toothpick.openScope(RssReaderApplication.instance))
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(R.string.app_name)
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                builder.create().show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.initDefaultList()
        (application as RssReaderApplication).internetConnectionListener = this
    }

    override fun onInternetUnavailable() {
        runOnUiThread(object: Runnable {
            override fun run() {
                val toast = Toast.makeText(
                    applicationContext,
                    R.string.network_error,
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()
            }
        })
    }
}