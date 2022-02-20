package dev.rssreader.domain.usecase

import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.domain.repositories.RssChannelNewsRepository
import dev.rssreader.entity.network.Channel
import dev.rssreader.entity.network.Image
import dev.rssreader.entity.network.Item
import dev.rssreader.entity.network.Rss
import dev.rssreader.entity.repositories.RssChannelNewsRepositoryImpl
import dev.rssreader.entity.repositories.datasource.RemoteDataSource
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class GetRssChannelNewsTest {

    companion object {
        private const val URL_CHANNEL_TEST = "https://news.mail.ru/rss/91/"
        private const val TITLE_CHANNEL_TEST = "Новости Mail.ru"
        private const val IMAGE_CHANNEL_TEST = "https://news.mail.ru/img/logo/news/news_web.png"

        private const val TITLE_ITEM_TEST = "Над Алабамой появилось дырявое облако с «хвостом»"
        private const val LINK_ITEM_TEST = "https://news.mail.ru/society/50134180"
        private const val DESCRIPTION_ITEM_TEST = "Удивительное природное явление."
        private const val PUBDATE_ITEM_TEST = "Sun, 20 Feb 2022 17:25:09 +0300"
    }

    lateinit var getRssChannelNews: GetRssChannelNews
    lateinit var repository: RssChannelNewsRepository

    private val dataSource: RemoteDataSource = mock()

    @Before
    fun setUp() {
        repository = RssChannelNewsRepositoryImpl(dataSource)
        getRssChannelNews = GetRssChannelNews(repository)
    }

    @Test
    fun check_Observe_Success() {
        whenever(dataSource.getRssChannelNews(URL_CHANNEL_TEST)).thenReturn(Observable.just(mockRss()))

        val testObservable = getRssChannelNews.getRssChannelNews(URL_CHANNEL_TEST).test()

        testObservable.assertResult(mockRssChannelNewsDataList())
        testObservable.assertComplete()
    }

    @Test
    fun check_Observe_Error() {
        whenever(dataSource.getRssChannelNews(URL_CHANNEL_TEST)).thenReturn(Observable.error(RuntimeException()))

        val testObservable = getRssChannelNews.getRssChannelNews(URL_CHANNEL_TEST).test()

        testObservable.assertError(RuntimeException::class.java)
    }

    private fun mockRss(): Rss {
        return Rss(
            Channel(
                title = TITLE_CHANNEL_TEST,
                image = Image(IMAGE_CHANNEL_TEST),
                items = listOf(
                    Item(
                        title = TITLE_ITEM_TEST,
                        link = LINK_ITEM_TEST,
                        description = DESCRIPTION_ITEM_TEST,
                        pubDate = PUBDATE_ITEM_TEST
                    )
                )
            )
        )
    }

    private fun mockRssChannelNewsDataList(): List<RssChannelNewsData> {
        return listOf(
            RssChannelNewsData(
                title = TITLE_ITEM_TEST,
                date = LocalDateTime.parse(PUBDATE_ITEM_TEST, DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z")),
                link = LINK_ITEM_TEST,
                description = DESCRIPTION_ITEM_TEST
            )
        )
    }
}