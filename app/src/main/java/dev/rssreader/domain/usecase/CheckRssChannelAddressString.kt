package dev.rssreader.domain.usecase

import io.reactivex.Single

class CheckRssChannelAddressString {
    companion object {
        val URL_REGEX = "^(https?://)?([\\w-А-Яа-я])+(\\.[\\w-А-Яа-я])+([\\w.-А-Яа-я/])*"

        fun isCorrectRssChannelAddressString(rsschannel: String): Single<Boolean> {
            return Single.just(rsschannel)
                .map { it.matches(Regex(URL_REGEX)) }
        }
    }
}