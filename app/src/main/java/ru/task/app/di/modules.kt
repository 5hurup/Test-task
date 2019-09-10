package ru.task.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.task.app.domain.usecase.contacts.ContactsUseCase
import ru.task.app.presentation.flow.contacts.ContactsViewModel

internal val useCasesModule = module {
    factory<ContactsUseCase> { ContactsUseCase(get()) }
}

internal val viewModelsModule = module {
    viewModel<ContactsViewModel> { ContactsViewModel(get()) }
}