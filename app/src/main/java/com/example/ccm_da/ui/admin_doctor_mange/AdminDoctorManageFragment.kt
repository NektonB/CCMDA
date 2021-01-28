package com.example.ccm_da.ui.admin_doctor_mange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ccm_da.R

class AdminDoctorManageFragment : Fragment() {

    private lateinit var adminDoctorManageViewModel: AdminDoctorManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adminDoctorManageViewModel =
            ViewModelProvider(this).get(AdminDoctorManageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_admin_doctor_manage, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        adminDoctorManageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}