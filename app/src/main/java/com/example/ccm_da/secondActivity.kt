package com.example.ccm_da

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class secondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val button = findViewById<Button>(R.id.coorButton)
        button.setOnClickListener {
            val intent = Intent(this, medicalCenter::class.java)
            startActivity(intent)
        }
    }
}