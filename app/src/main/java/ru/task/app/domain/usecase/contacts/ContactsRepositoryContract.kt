package ru.task.app.domain.usecase.contacts

import kotlinx.coroutines.flow.Flow
import org.joda.time.DateTime
import ru.task.app.domain.entity.ContactVO

interface ContactsRepositoryContract {
    fun getContacts(isRequireUpdate: Boolean): Flow<List<ContactVO>>
    fun getLastUpdatedTime(): DateTime?
}