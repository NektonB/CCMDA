package com.example.ccm_da.ui.admin_user_manage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdminUserManageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is admin_user_manage Fragment"
    }
    val text: LiveData<String> = _text
}