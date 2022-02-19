package dev.rssreader.entity.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface RequestService {
    @GET
    fun getRssChannelNews(@Url rsschannelurl: String): Observable<Rss>
}