package ru.task.app.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.joda.time.DateTime
import ru.task.app.data.db.AppDatabase
import ru.task.app.data.db.entity.Contact
import ru.task.app.data.entity.ContactDTO
import ru.task.app.data.entity.ContactDTO.TemperamentDTO.CHOLERIC
import ru.task.app.data.entity.ContactDTO.TemperamentDTO.MELANCHOLIC
import ru.task.app.data.entity.ContactDTO.TemperamentDTO.PHLEGMATIC
import ru.task.app.data.entity.ContactDTO.TemperamentDTO.SANGUINE
import ru.task.app.data.network.api.media.MediaContentApi
import ru.task.app.data.prefs.SharedPrefs
import ru.task.app.domain.entity.ContactVO
import ru.task.app.domain.entity.ContactVO.EducationPeriod
import ru.task.app.domain.entity.ContactVO.Temperament
import ru.task.app.domain.usecase.contacts.ContactsRepositoryContract

internal class ContactsRepository(
    private val api: MediaContentApi,
    private val db: AppDatabase,
    private val sharedPrefs: SharedPrefs
) : ContactsRepositoryContract {

    private val remoteFiles = listOf("generated-01", "generated-02", "generated-03")

    override fun getContacts(isRequireUpdate: Boolean): Flow<List<ContactVO>> =
        flow {
            if (isRequireUpdate) {
                sharedPrefs.contactsLastUpdateTime = DateTime.now()
                val contactsRemote = mutableListOf<ContactVO>()
                remoteFiles.forEach { filename ->
                    contactsRemote += loadContacts(filename)
                }
                db.contactsDao().clearAndInsert(contactsRemote.map { it.mapToDTO() })
                emit(contactsRemote)
            } else {
                val contactsLocal = db.contactsDao().getAll().map { it.mapToVO() }
                emit(contactsLocal)
            }
        }

    override fun getLastUpdatedTime(): DateTime? = sharedPrefs.contactsLastUpdateTime

    private suspend fun loadContacts(filename: String): List<ContactVO> =
        api.getContactsAsync(filename).await().map { it.mapToVO() }

    private fun Contact.mapToVO(): ContactVO = ContactVO(
        uid,
        name,
        height,
        phone,
        biography,
        temperament,
        EducationPeriod(educationPeriodStart, educationPeriodEnd)
    )

    private fun ContactDTO.mapToVO(): ContactVO = ContactVO(
        id,
        name,
        height,
        phone,
        biography,
        toTemperament(),
        EducationPeriod(educationPeriod.start, educationPeriod.end)
    )

    private fun ContactDTO.toTemperament(): Temperament = when (temperament) {
        MELANCHOLIC -> Temperament.MELANCHOLIC
        PHLEGMATIC -> Temperament.PHLEGMATIC
        SANGUINE -> Temperament.SANGUINE
        CHOLERIC -> Temperament.CHOLERIC
    }

    private fun ContactVO.mapToDTO(): Contact = Contact(
        id,
        name,
        height,
        phone,
        biography,
        temperament,
        educationPeriod.start,
        educationPeriod.end
    )
}