package com.example.ccm_da

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val button = findViewById<Button>(R.id.medcenterButton)
        button.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }
    }
}