package com.example.ccm_da

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MedicalCenterReg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_center)

        initialize()
    }


    private fun initialize() {
        val btnNext = findViewById<Button>(R.id.btnSave)
        btnNext.setOnClickListener {
            val iSignUp = Intent(this, SignUpActivity::class.java)
            startActivity(iSignUp)
        }
    }
}