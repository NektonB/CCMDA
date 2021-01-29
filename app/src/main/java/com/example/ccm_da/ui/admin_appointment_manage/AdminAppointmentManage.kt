package com.example.ccm_da.ui.admin_appointment_manage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ccm_da.R.layout

class AdminAppointmentManage : Fragment() {

    companion object {
        fun newInstance() = AdminAppointmentManage()
    }

    private lateinit var viewModel: AdminAppointmentManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_admin_appointment_manage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminAppointmentManageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}