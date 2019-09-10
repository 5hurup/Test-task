package ru.task.app.presentation.flow.contacts.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.task.app.domain.entity.ContactVO

class ContactsDiffCallback(
    private val oldContacts: List<ContactVO>,
    private val newContacts: List<ContactVO>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldContacts[oldItemPosition].id == newContacts[newItemPosition].id

    override fun getOldListSize(): Int = oldContacts.size

    override fun getNewListSize(): Int = newContacts.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldContacts[oldItemPosition] == newContacts[newItemPosition]
}