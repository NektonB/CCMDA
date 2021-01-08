package com.example.ccm_da

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PersonChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_choose)

        initialize()
    }

    private fun initialize() {
        val btnCoordinator = findViewById<Button>(R.id.btnCoordinator)
        val btnDoctor = findViewById<Button>(R.id.btnDoctor)

        val iSignUp = Intent(this, SignUpActivity::class.java)

        btnCoordinator.setOnClickListener {
            iSignUp.putExtra("PERSON", "COORDINATOR")
            startActivity(iSignUp)
        }

        btnDoctor.setOnClickListener {
            iSignUp.putExtra("PERSON", "DOCTOR")
            startActivity(iSignUp)
        }
    }
}