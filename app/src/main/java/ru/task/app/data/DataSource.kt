package ru.task.app.data

import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import ru.task.app.data.di.apiModule
import ru.task.app.data.di.storageModule
import ru.task.app.data.di.httpClientModule
import ru.task.app.data.di.repositoryModule

object DataSource : KoinComponent {
    fun init() = loadKoinModules(
        listOf(
            httpClientModule,
            apiModule,
            storageModule,
            repositoryModule
        )
    )
}