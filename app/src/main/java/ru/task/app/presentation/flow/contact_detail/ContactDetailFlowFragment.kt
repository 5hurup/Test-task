package ru.task.app.presentation.flow.contact_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.fragment_flow_contact_detail.biography
import kotlinx.android.synthetic.main.fragment_flow_contact_detail.education_period
import kotlinx.android.synthetic.main.fragment_flow_contact_detail.name
import kotlinx.android.synthetic.main.fragment_flow_contact_detail.phone
import kotlinx.android.synthetic.main.fragment_flow_contact_detail.temperament
import kotlinx.android.synthetic.main.fragment_flow_contact_detail.toolbar
import ru.task.app.R
import ru.task.app.R.string
import ru.task.app.domain.entity.ContactVO
import ru.task.app.domain.entity.ContactVO.Temperament.CHOLERIC
import ru.task.app.domain.entity.ContactVO.Temperament.MELANCHOLIC
import ru.task.app.domain.entity.ContactVO.Temperament.PHLEGMATIC
import ru.task.app.domain.entity.ContactVO.Temperament.SANGUINE
import ru.task.app.presentation.common.base.fragment.FlowFragment

class ContactDetailFlowFragment : FlowFragment() {
    private val contact: ContactVO by lazy {
        ContactDetailFlowFragmentArgs.fromBundle(arguments!!).contact
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_flow_contact_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
        showContactDetail()
    }

    private fun showContactDetail() {
        name.text = contact.name
        phone.text = contact.phone
        temperament.text = when (contact.temperament) {
            MELANCHOLIC -> getString(string.melancholic)
            PHLEGMATIC -> getString(string.phlegmatic)
            SANGUINE -> getString(string.sanguine)
            CHOLERIC -> getString(string.choleric)
        }
        education_period.text = String.format(
            "%s - %s",
            contact.educationPeriod.start.toString("dd.MM.yyyy"),
            contact.educationPeriod.end.toString("dd.MM.yyyy")
        )
        biography.text = contact.biography
    }
}