package ru.task.app.domain.usecase.contacts

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.joda.time.DateTime
import ru.task.app.domain.entity.ContactVO
import java.util.concurrent.TimeUnit

class ContactsUseCase(private val contactsRepository: ContactsRepositoryContract) {
    private val updatePeriodMillis = TimeUnit.MINUTES.toMillis(1)
    private val phoneFilter = "\\D*".toRegex()

    fun getContacts(
        searchKeyword: String? = null,
        isRequireUpdate: Boolean = false
    ): Flow<List<ContactVO>> = contactsRepository.getContacts(isRequireUpdate || isTimeToUpdate()).map { contacts ->
        searchKeyword?.let { keyword -> filterContacts(keyword, contacts) } ?: contacts
    }

    private fun filterContacts(keyword: String, contacts: List<ContactVO>): List<ContactVO> =
        contacts.filter { it.name.contains(keyword, true) || it.phone.replace(phoneFilter, "").contains(keyword) }

    private fun isTimeToUpdate(): Boolean = contactsRepository.getLastUpdatedTime()?.let {
        DateTime.now().minus(it.millis).millis > updatePeriodMillis
    } ?: true
}