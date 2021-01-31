package com.example.ccm_da.ui.admin_user_manage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ccm_da.R

class UserAdapter(val context: Context?, val users: List<User>, val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_card_view, parent, false)
        return UserViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(users[position], context)
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    inner class UserViewHolder(itemView: View, val onItemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val ivUser: ImageView = itemView.findViewById(R.id.ivUser)
        private val tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
        private val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        private val tvUserType: TextView = itemView.findViewById(R.id.tvUserType)
        private val ivEdit: ImageView = itemView.findViewById(R.id.ivEdit)
        private val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)

        fun bindData(user: User, context: Context?) {
            ivUser.setImageResource(user.resourceId)
            tvFullName.text = (user.fullName)
            tvUserName.text = (user.userName)
            tvUserType.text = (user.userType)

            ivUser.setOnClickListener {
                onItemClick(user.id)
            }

            ivEdit.setOnClickListener {
                onItemClick(user.id)
            }

            ivDelete.setOnClickListener {
                onItemClick(user.id)
            }
        }
    }
}