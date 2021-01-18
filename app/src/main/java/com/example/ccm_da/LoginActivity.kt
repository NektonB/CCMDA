package com.example.ccm_da

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialize()
    }

    private fun initialize() {
        val etSingUp = findViewById<TextView>(R.id.etSingUp)
        etSingUp.setOnClickListener {
            val iChoosePerson = Intent(this, PersonChooseActivity::class.java)
            startActivity(iChoosePerson)
        }

        val btnlogin = findViewById<TextView>(R.id.btnLogin)
        btnlogin.setOnClickListener {
            val iChoosePerson = Intent(this, ADActivity::class.java)
            startActivity(iChoosePerson)
        }
    }
}