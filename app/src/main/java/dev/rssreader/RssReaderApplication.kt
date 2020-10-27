package dev.rssreader

import android.app.Application
import dev.rssreader.di.modules.AppModule
import toothpick.Scope
import toothpick.Toothpick

class RssReaderApplication : Application() {

    private lateinit var appScope: Scope

    companion object {
        lateinit var instance: RssReaderApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appScope = Toothpick.openScope(this)
        appScope.installModules(AppModule(this))
    }


}