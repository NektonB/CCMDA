package com.example.ccm_da.ui.admin_center_mange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ccm_da.R

class CenterManageFragment : Fragment() {

    companion object {
        fun newInstance() = CenterManageFragment()
    }

    private lateinit var viewModel: CenterManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_center_manage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CenterManageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}