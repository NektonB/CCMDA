package com.example.ccm_da

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initialize()
    }

    private fun initialize() {
        val btnCoordinator = findViewById<Button>(R.id.btnCoordinator)

        btnCoordinator.setOnClickListener {
            val iMedicalCenterReg = Intent(this, MedicalCenterReg::class.java)
            startActivity(iMedicalCenterReg)
        }
    }
}