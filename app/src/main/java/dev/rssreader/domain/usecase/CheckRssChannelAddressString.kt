package dev.rssreader.domain.usecase

import io.reactivex.Observable

class CheckRssChannelAddressString {
    companion object {
        val URL_REGEX = "^(https?://)?([\\w-А-Яа-я])+(\\.[\\w-А-Яа-я])+([\\w.-А-Яа-я/])*"

        fun isCorrectRssChannelAddressString(rsschannel: String): Observable<Boolean> {
            return Observable.just(rsschannel)
                .map { it.matches(Regex(URL_REGEX)) }
        }
    }
}