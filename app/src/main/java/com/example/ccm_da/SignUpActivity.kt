package com.example.ccm_da

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ccm_da.db_conn.DatabaseConn
import com.example.ccm_da.pojos.User

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initialize()
    }

    private fun initialize() {
        val btnSingUp: Button = findViewById(R.id.btnSingUp)
        val tvLogin: TextView = findViewById(R.id.tvLogin)
        val btnAddCenter: Button = findViewById(R.id.btnRegCenter)

        btnSingUp.setOnClickListener {

            val saveUser = saveUser()
            if (saveUser) {
                val iEditProfile = Intent(this, EditProfileActivity::class.java)
                startActivity(iEditProfile)
            }
        }

        tvLogin.setOnClickListener {
            val iLogin = Intent(this, LoginActivity::class.java)
            startActivity(iLogin)
        }

        btnAddCenter.setOnClickListener {
            val iMedicalCenterReg = Intent(this, MedicalCenterReg::class.java)
            startActivity(iMedicalCenterReg)
        }

    }

    private fun saveUser(): Boolean {
        var operation = false
        try {
            val centerNumber = findViewById<EditText>(R.id.etCenterNumber).text.toString()
            val userName = findViewById<EditText>(R.id.etUserName).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()

            var user: User = User()
            user.user_name = userName
            user.password = password

            DatabaseConn.getUserRef().document(userName).set(user).addOnSuccessListener {
                Toast.makeText(this, "User Saved", Toast.LENGTH_SHORT).show()
                operation = true
            }.addOnFailureListener { it: Exception ->
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                operation = false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(this.toString(), e.toString())
        }
        Toast.makeText(this, "Operation : $operation", Toast.LENGTH_SHORT).show()
        return operation
    }
}