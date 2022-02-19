package dev.rssreader.di.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.rssreader.domain.repositories.RssChannelNewsRepository
import dev.rssreader.domain.repositories.RssChannelsRepository
import dev.rssreader.entity.network.InternetConnectionListener
import dev.rssreader.entity.repositories.RssChannelNewsRepositoryImpl
import dev.rssreader.entity.repositories.RssChannelsRepositoryImpl
import dev.rssreader.entity.repositories.datasource.LocalDataSource
import dev.rssreader.entity.repositories.datasource.RemoteDataSource
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindRssChannelsRepository(rssChannelsRepositoryImpl: RssChannelsRepositoryImpl): RssChannelsRepository

    @Binds
    abstract fun bindRssChannelNewsRepository(rssChannelNewsRepositoryImpl: RssChannelNewsRepositoryImpl): RssChannelNewsRepository

    companion object AppModuleProvides {
        @Singleton
        @Provides
        fun localDataSource(@ApplicationContext context: Context): LocalDataSource {
            return LocalDataSource(context)
        }

        @Singleton
        @Provides
        fun remoteDataSource(
            @ApplicationContext context: Context,
            internetConnectionListener: InternetConnectionListener
        ): RemoteDataSource {
            return RemoteDataSource(context, internetConnectionListener)
        }

        @Singleton
        @Provides
        fun internetConnectionListener(): InternetConnectionListener {
            return InternetConnectionListener(BehaviorSubject.create())
        }
    }
}