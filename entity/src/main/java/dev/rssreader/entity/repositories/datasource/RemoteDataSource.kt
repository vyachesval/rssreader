package dev.rssreader.entity.repositories.datasource

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.rssreader.entity.network.InternetConnectionListener
import dev.rssreader.entity.network.NetworkConnectionInterceptor
import dev.rssreader.entity.network.RequestService
import dev.rssreader.entity.network.Rss
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    @ApplicationContext val context: Context,
    private val internetConnectionListener: InternetConnectionListener
) {

    val subject = PublishSubject.create<Pair<String, Rss>>()

    fun getRssChannelNews(rsschannelUrl: String): Observable<Rss> {

        val uri = Uri.parse(rsschannelUrl)
        val baseUrl = uri.scheme + "://" + uri.host + "/"

        val httpclientBuilder = OkHttpClient.Builder()
        httpclientBuilder.addInterceptor(object : NetworkConnectionInterceptor() {
            override fun isInternetAvailable(): Boolean {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val capabilities: NetworkCapabilities? =
                        connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork())
                    return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                } else {
                    val activeNetworkInfo = connectivityManager.activeNetworkInfo
                    return activeNetworkInfo != null && activeNetworkInfo.isConnected
                }
            }

            override fun onInternetUnavailable() {
                internetConnectionListener.subject.onNext(Unit)
            }
        })

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpclientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        val requestService = retrofit.create(RequestService::class.java)

        return requestService.getRssChannelNews(rsschannelUrl)
            .doOnNext { subject.onNext(Pair<String, Rss>(rsschannelUrl, it)) }

    }
}