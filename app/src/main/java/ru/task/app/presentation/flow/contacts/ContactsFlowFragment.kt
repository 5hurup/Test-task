package ru.task.app.presentation.flow.contacts

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_flow_contacts.contacts
import kotlinx.android.synthetic.main.fragment_flow_contacts.progress_bar
import kotlinx.android.synthetic.main.fragment_flow_contacts.search_value
import kotlinx.android.synthetic.main.fragment_flow_contacts.swiperefresh
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.task.app.R
import ru.task.app.R.string
import ru.task.app.domain.entity.ContactVO
import ru.task.app.extensions.show
import ru.task.app.extensions.showSnackbar
import ru.task.app.presentation.common.base.fragment.FlowFragment
import ru.task.app.presentation.flow.contact_detail.ContactDetailFlowFragmentArgs
import ru.task.app.presentation.flow.contacts.adapter.ContactItemDecorator
import ru.task.app.presentation.flow.contacts.adapter.ContactsAdapter
import java.net.UnknownHostException
import java.util.Timer
import java.util.TimerTask

class ContactsFlowFragment : FlowFragment() {
    private val viewModel: ContactsViewModel by viewModel()
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.contactsResponse.observe(
            this, StateDataProcessor(::showContacts, {
                swiperefresh.isRefreshing = false
                showSnackbar(
                    when (it) {
                        is UnknownHostException -> getString(string.no_internet_connection)
                        else -> getString(string.something_went_wrong)
                    }
                )
            })
        )
        contactsAdapter = ContactsAdapter {
            findNavController().navigate(
                R.id.contact_detail_flow_dest,
                ContactDetailFlowFragmentArgs.Builder(it).build().toBundle()
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_flow_contacts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contacts.addItemDecoration(
            ContactItemDecorator(
                ContextCompat.getDrawable(requireContext(), R.drawable.item_divider)!!,
                resources.getDimensionPixelSize(R.dimen.dp16)
            )
        )
        contacts.adapter = contactsAdapter
        swiperefresh.setOnRefreshListener {
            viewModel.getContacts(isRequireUpdate = true)
        }
        search_value.addTextChangedListener(object : TextWatcher {
            private var timer = Timer()
            private val delay: Long = 1000

            override fun afterTextChanged(s: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            viewModel.getContacts(s?.toString())
                        }
                    }, delay
                )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun showContacts(contacts: List<ContactVO>) {
        swiperefresh.isRefreshing = false
        contactsAdapter.showContacts(contacts)
    }

    override fun enableLoading(state: Boolean) {
        progress_bar.show(state && swiperefresh.isRefreshing.not())
    }
}