package dev.rssreader.domain.usecase

class CheckRssChannelAddressString {
    companion object {
        val URL_REGEX = "^(https?://)?([\\w-А-Яа-я])+(\\.[\\w-А-Яа-я])+([\\w.-А-Яа-я/])*"

        fun isCorrectRssChannelAddressString(rsschannel: String): Boolean {
            return rsschannel.matches(Regex(URL_REGEX))
        }
    }
}