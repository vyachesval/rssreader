package dev.rssreader

import android.app.Application
import dev.rssreader.di.modules.AppModule
import toothpick.Scope
import toothpick.Toothpick

class RssReaderApplication : Application() {

    lateinit var appScope: Scope

    override fun onCreate() {
        super.onCreate()
        appScope = Toothpick.openScope(this)
        appScope.installModules(AppModule(this))
    }

}