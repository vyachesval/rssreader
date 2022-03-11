package dev.rssreader.rss.list

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import dagger.hilt.android.AndroidEntryPoint
import dev.rssreader.rss.R2

@AndroidEntryPoint
open class ListFragment : Fragment() {
    @BindView(R2.id.viewitemlist)
    protected lateinit var rsschannelsList: RecyclerView
}