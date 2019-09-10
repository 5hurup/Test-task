package ru.task.app.presentation.flow.contacts

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.task.app.domain.entity.ContactVO
import ru.task.app.domain.usecase.contacts.ContactsUseCase
import ru.task.app.presentation.common.base.BaseViewModel
import ru.task.app.presentation.common.base.livedata.MutableLiveStateData

class ContactsViewModel(private val contactsUseCase: ContactsUseCase) : BaseViewModel() {
    val contactsResponse = MutableLiveStateData<List<ContactVO>>()

    init {
        getContacts()
    }

    fun getContacts(searchKeyword: String? = null, isRequireUpdate: Boolean = false) = launch {
        try {
            contactsResponse.setLoading()
            withContext(Dispatchers.IO) {
                contactsUseCase.getContacts(searchKeyword, isRequireUpdate).collect {
                    withContext(Dispatchers.Main) {
                        contactsResponse.setData(it)
                    }
                }
            }
        } catch (ex: Exception) {
            contactsResponse.setError(ex)
        }
    }
}