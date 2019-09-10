package ru.task.app.presentation.flow.contacts

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_flow_contacts.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.task.app.R
import ru.task.app.R.string
import ru.task.app.domain.entity.ContactVO
import ru.task.app.domain.exceptions.NoInternetException
import ru.task.app.extensions.show
import ru.task.app.presentation.common.base.fragment.FlowFragment
import ru.task.app.presentation.flow.contact_detail.ContactDetailFlowFragmentArgs
import ru.task.app.presentation.flow.contacts.adapter.ContactItemDecorator
import ru.task.app.presentation.flow.contacts.adapter.ContactsAdapter
import java.net.UnknownHostException

class ContactsFlowFragment : FlowFragment() {
    private val viewModel: ContactsViewModel by viewModel()
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactsAdapter = ContactsAdapter {
            findNavController().navigate(
                R.id.contact_detail_flow_dest,
                ContactDetailFlowFragmentArgs.Builder(it).build().toBundle()
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.contactsResponse.observe(
            viewLifecycleOwner, StateDataProcessor(::showContacts, { ex ->
                swiperefresh.isRefreshing = false
                when (ex) {
                    is UnknownHostException -> NoInternetException(getString(string.no_internet_connection))
                    else -> ex
                }.let { showError(it) }
            })
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
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
            override fun afterTextChanged(s: Editable?) {
                viewModel.getContacts(s?.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
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