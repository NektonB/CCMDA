package com.example.ccm_da.ui.admin_user_manage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        //val textView: TextView = root.findViewById(R.id.text_gallery)

        println("Resource Id : " + R.drawable.doctor)

        //textView.text = "Fuck It"
        var userId = 0

        val u1: User = User(1, 2131165316, "No Name", "Admin", "Admin")
        val u2: User = User(2, 2131165316, "No Name", "Admin", "Admin")
        val u3: User = User(3, 2131165316, "No Name", "Admin", "Admin")
        val u4: User = User(4, 2131165316, "No Name", "Admin", "Admin")
        val u5: User = User(5, 2131165316, "No Name", "Admin", "Admin")
        val u6: User = User(6, 2131165316, "No Name", "Admin", "Admin")

        var userList = mutableListOf<User>()
        userList.add(u1)
        userList.add(u2)
        userList.add(u3)
        userList.add(u4)
        userList.add(u5)
        userList.add(u6)

        var userAdapter: UserAdapter = UserAdapter(context, userList) { userId ->

        }

        val lmUser = GridLayoutManager(context, 2)
        root.findViewById<RecyclerView>(R.id.rv_user).adapter = userAdapter
        root.findViewById<RecyclerView>(R.id.rv_user).layoutManager = lmUser

        return root
    }


}