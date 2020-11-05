package dev.rssreader.entity.repositories.datasource

import android.net.Uri
import dev.rssreader.entity.network.RequestService
import dev.rssreader.entity.network.Rss
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    fun getRssChannelNews(rsschannelUrl: String): Observable<Rss> {

        val uri = Uri.parse(rsschannelUrl)
        val baseUrl = uri.scheme + "://" + uri.host + "/"

        val httpclientBuilder = OkHttpClient.Builder()
        httpclientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val response = chain.proceed(chain.request())
                return response
            }
        })

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpclientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        val requestService  = retrofit.create(RequestService::class.java)

        return requestService.getRssChannelNews(rsschannelUrl)

    }
}