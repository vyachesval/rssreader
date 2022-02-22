package dev.rssreader

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.rssreader.presentation.screen.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.BoundedMatcher
import dev.rssreader.presentation.list.channels.RssChannelListViewHolder
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not


@RunWith(AndroidJUnit4::class)
class RssChannelFlowTest {

    companion object {

        private const val URL_CHANNEL_TEST = "https://news.mail.ru/rss/91/"
    }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkAddRssChannel() {
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.input_rsschannel)).perform(typeText(URL_CHANNEL_TEST))
        onView(withText(R.string.add))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.viewitemlist))
            .check(matches(atLastPosition(hasDescendant(withText(URL_CHANNEL_TEST)))))
    }

    @Test
    fun checkDelRssChannel() {
        val matcher = viewHolderWithText(URL_CHANNEL_TEST)
        onView(withId(R.id.viewitemlist)).perform(
            scrollToHolder(matcher),
            actionOnHolderItem(matcher, longClick())
        )
        onView(withText(R.string.del))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.viewitemlist))
            .check(matches(not(hasItem(hasDescendant(withText(URL_CHANNEL_TEST))))))
    }

    private fun atLastPosition(matcher: Matcher<View>) = RecyclerAtPositionMatcher(matcher)
    class RecyclerAtPositionMatcher(
        private val matcher: Matcher<View>
    ) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("has item at last position: ")
            matcher.describeTo(description)
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            val countItems = item?.adapter?.itemCount ?: 0
            return matcher.matches(item?.findViewHolderForAdapterPosition(countItems - 1)?.itemView)
        }
    }

    private fun hasItem(matcher: Matcher<View>) = RecyclerHasItemMatcher(matcher)
    class RecyclerHasItemMatcher(
        private val matcher: Matcher<View>
    ) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("has item: ")
            matcher.describeTo(description)
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            val adapter = item?.adapter
            adapter?.let {
                val countItems = adapter.itemCount - 1
                for (i in 0..countItems) {
                    val holder = adapter.createViewHolder(item, adapter.getItemViewType(i))
                    adapter.bindViewHolder(holder, i)
                    if (matcher.matches(holder.itemView))
                        return true
                }
            }
            return false
        }
    }

    private fun viewHolderWithText(text: String) = ViewHolderWithTextMatcher(text)
    class ViewHolderWithTextMatcher(
        private val text: String
    ) : BoundedMatcher<RecyclerView.ViewHolder, RssChannelListViewHolder>(RssChannelListViewHolder::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("has item with text ${text}: ")
        }

        override fun matchesSafely(item: RssChannelListViewHolder?): Boolean {
            return item?.addressView?.text == text
        }
    }
}


