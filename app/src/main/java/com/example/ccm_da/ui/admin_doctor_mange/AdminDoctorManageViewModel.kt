package com.example.ccm_da.ui.admin_doctor_mange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdminDoctorManageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is admin_doctor_mange Fragment"
    }
    val text: LiveData<String> = _text
}