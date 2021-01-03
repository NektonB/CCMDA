package com.example.ccm_da

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
            val iEditProfile = Intent(this, EditProfileActivity::class.java)
            startActivity(iEditProfile)
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
}