package dev.rssreader.presentation.list

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import dagger.hilt.android.AndroidEntryPoint
import dev.rssreader.R

@AndroidEntryPoint
open class ListFragment : Fragment() {
    @BindView(R.id.viewitemlist)
    protected lateinit var rsschannelsList: RecyclerView
}