package dev.rssreader.entity.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import dev.rssreader.domain.entity.RssChannelNewsData
import dev.rssreader.domain.repositories.RssChannelNewsRepository
import io.reactivex.Observable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RssChannelNewsRepositoryImpl @Inject constructor() : RssChannelNewsRepository{

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getRssChannelNews(rsschannelUrl: String): Observable<List<RssChannelNewsData>> {
        val time = LocalDateTime.parse("Mon, 02 Nov 2020 14:27:13 +0300"
            , DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z"))
        val news = RssChannelNewsData(1
            ,"В Нидерландах скульптура спасла поезд метро от падения в воду"
            , time
            , "https://www.yaplakal.com/forum1/topic2186098.html"
            , "<![CDATA[ Скульптура &quot;Хвосты китов&quot; в голландском городе Спейкениссе предотвратила падение в воду поезда метро, который врезался в барьер в конце путей на открытой станции, сообщает портал Dutch News.<br><br>Согласно порталу, около полуночи поезд выехал за пределы путей и застрял на скульптуре на высоте 10 метров над пешеходной дорожкой. Машинист смог покинуть поезд самостоятельно, пассажиров в это время в поезде не было.<br><br>Скульптура &quot;Хвосты китов&quot; изготовлена из полиэстра голландским архитектором Маартеном Струйсом. Она была размещена в водоеме в конце путей станции метро в 2002 году.<br><br><a href='/go/?https%3A%2F%2Fria.ru%2F20201102%2Fniderlandy-1582650717.html' target='_blank' class=''>Via</a> <br><br><a href=\"https://www.yaplakal.com/forum1/topic2186098.html\" target=\"_blank\"><img src=\"https://s00.yaplakal.com/pics/pics_preview/2/2/3/14887322.jpg\" border=\"0\"></a> ]]>"
        )
        val news1 = RssChannelNewsData(2
            ,"111111111111111111111111111"
            , time
            , "https://www.yaplakal.com/forum1/topic2186098.html"
            , "<![CDATA[ Скульптура &quot;Хвосты китов&quot; в голландском городе Спейкениссе предотвратила падение в воду поезда метро, который врезался в барьер в конце путей на открытой станции, сообщает портал Dutch News.<br><br>Согласно порталу, около полуночи поезд выехал за пределы путей и застрял на скульптуре на высоте 10 метров над пешеходной дорожкой. Машинист смог покинуть поезд самостоятельно, пассажиров в это время в поезде не было.<br><br>Скульптура &quot;Хвосты китов&quot; изготовлена из полиэстра голландским архитектором Маартеном Струйсом. Она была размещена в водоеме в конце путей станции метро в 2002 году.<br><br><a href='/go/?https%3A%2F%2Fria.ru%2F20201102%2Fniderlandy-1582650717.html' target='_blank' class=''>Via</a> <br><br><a href=\"https://www.yaplakal.com/forum1/topic2186098.html\" target=\"_blank\"><img src=\"https://s00.yaplakal.com/pics/pics_preview/2/2/3/14887322.jpg\" border=\"0\"></a> ]]> <![CDATA[ Скульптура &quot;Хвосты китов&quot; в голландском городе Спейкениссе предотвратила падение в воду поезда метро, который врезался в барьер в конце путей на открытой станции, сообщает портал Dutch News.<br><br>Согласно порталу, около полуночи поезд выехал за пределы путей и застрял на скульптуре на высоте 10 метров над пешеходной дорожкой. Машинист смог покинуть поезд самостоятельно, пассажиров в это время в поезде не было.<br><br>Скульптура &quot;Хвосты китов&quot; изготовлена из полиэстра голландским архитектором Маартеном Струйсом. Она была размещена в водоеме в конце путей станции метро в 2002 году.<br><br><a href='/go/?https%3A%2F%2Fria.ru%2F20201102%2Fniderlandy-1582650717.html' target='_blank' class=''>Via</a> <br><br><a href=\"https://www.yaplakal.com/forum1/topic2186098.html\" target=\"_blank\"><img src=\"https://s00.yaplakal.com/pics/pics_preview/2/2/3/14887322.jpg\" border=\"0\"></a> ]]>"
        )
        return Observable.just(listOf(news, news1))
    }
}