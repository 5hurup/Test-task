package ru.task.app.presentation.flow.contacts.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_contact.view.man_height
import kotlinx.android.synthetic.main.item_contact.view.name
import kotlinx.android.synthetic.main.item_contact.view.phone
import ru.task.app.R
import ru.task.app.domain.entity.ContactVO
import ru.task.app.extensions.inflate
import ru.task.app.presentation.common.BindedViewHolder
import ru.task.app.presentation.flow.contacts.adapter.ContactsAdapter.ContactVH

class ContactsAdapter(
    private val callback: (item: ContactVO) -> Unit
) : RecyclerView.Adapter<ContactVH>() {

    private var items = listOf<ContactVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactVH =
        ContactVH(parent.inflate(R.layout.item_contact))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ContactVH, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { callback(items[position]) }
    }

    fun showContacts(contacts: List<ContactVO>) {
        val diffResult = DiffUtil.calculateDiff(ContactsDiffCallback(items, contacts))
        items = contacts
        diffResult.dispatchUpdatesTo(this)
    }

    class ContactVH(view: View) : BindedViewHolder<ContactVO>(view) {
        override fun bind(data: ContactVO) {
            itemView.apply {
                name.setTextFuture(
                    PrecomputedTextCompat.getTextFuture(
                        data.name,
                        TextViewCompat.getTextMetricsParams(name),
                        null
                    )
                )
                man_height.setTextFuture(
                    PrecomputedTextCompat.getTextFuture(
                        data.height.toString(),
                        TextViewCompat.getTextMetricsParams(man_height),
                        null
                    )
                )
                phone.setTextFuture(
                    PrecomputedTextCompat.getTextFuture(
                        data.phone,
                        TextViewCompat.getTextMetricsParams(phone),
                        null
                    )
                )
            }
        }
    }
}