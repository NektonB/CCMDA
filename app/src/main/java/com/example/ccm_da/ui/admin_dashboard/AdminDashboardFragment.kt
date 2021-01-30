package com.example.ccm_da.ui.admin_dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ccm_da.R

class AdminDashboardFragment : Fragment() {

    private lateinit var adminDashboardViewModel: AdminDashboardViewModel
    private lateinit var root: View
    private lateinit var tvTest: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initialize(inflater, container, savedInstanceState)

        tvTest.text = "Test Pass"

        return root
    }

    private fun initialize(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        adminDashboardViewModel =
            ViewModelProvider(this).get(AdminDashboardViewModel::class.java)

        root = inflater.inflate(R.layout.fragment_admin_dashboard, container, false)

        tvTest = root.findViewById(R.id.tv_Test)
    }
}