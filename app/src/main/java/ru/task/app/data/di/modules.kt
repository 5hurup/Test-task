package ru.task.app.data.di

import androidx.room.Room
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.task.app.BuildConfig
import ru.task.app.data.db.AppDatabase
import ru.task.app.data.network.api.ApiProvider
import ru.task.app.data.network.api.media.MediaContentApi
import ru.task.app.data.network.http.HttpClient
import ru.task.app.data.prefs.SharedPrefs
import ru.task.app.data.repo.ContactsRepository
import ru.task.app.domain.usecase.contacts.ContactsRepositoryContract

internal val apiModule = module {
    single<MediaContentApi> { ApiProvider.createApiService(BuildConfig.SERVER_URL, get()) }
}

internal val httpClientModule = module {
    single<OkHttpClient>() { HttpClient.build { } }
}

internal val repositoryModule = module {
    single<ContactsRepositoryContract> { ContactsRepository(get(), get(), get()) }
}

internal val storageModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "database-name"
        ).build()
    }
    single<SharedPrefs> { SharedPrefs(androidContext()) }
}