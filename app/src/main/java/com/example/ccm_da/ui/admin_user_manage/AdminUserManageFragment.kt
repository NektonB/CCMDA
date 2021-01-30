package com.example.ccm_da.ui.admin_user_manage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ccm_da.R

class AdminUserManageFragment : Fragment() {

    private lateinit var adminUserManageViewModel: AdminUserManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adminUserManageViewModel =
            ViewModelProvider(this).get(AdminUserManageViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_admin_user_manage, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)

        textView.text = "Fuck It"
        return root
    }


}