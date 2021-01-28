package com.example.ccm_da.ui.admin_dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ccm_da.R

class AdminDashboardFragment : Fragment() {

    private lateinit var adminDashboardViewModel: AdminDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adminDashboardViewModel =
            ViewModelProvider(this).get(AdminDashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_admin_dashboard, container, false)

        adminDashboardViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}