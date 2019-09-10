package ru.task.app

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.task.app.data.DataSource
import ru.task.app.di.useCasesModule
import ru.task.app.di.viewModelsModule
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        JodaTimeAndroid.init(this)
        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelsModule, useCasesModule))
        }.let { DataSource.init() }
    }
}