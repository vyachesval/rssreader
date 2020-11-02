package dev.rssreader.presentation.screen.main

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import dev.rssreader.R
import dev.rssreader.presentation.screen.addchannel.AddRssChannelDialogFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.fab)
    lateinit var fabView: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)

        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        fabView.setOnClickListener {
            val addChannelDialog = AddRssChannelDialogFragment()
            val manager = supportFragmentManager
            addChannelDialog.show(manager, "AddRssChannel")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}