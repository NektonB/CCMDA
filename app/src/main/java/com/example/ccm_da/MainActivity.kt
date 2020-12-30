package com.example.ccm_da

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ListenerRegistration

class MainActivity : AppCompatActivity() {

    private var dbListener: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    override fun onStart() {
        super.onStart()
        dbListener = DatabaseConn.getDatabaseRef().addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(this, "Error while loading", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", error.toString())
            } else {
                Toast.makeText(this, "Load done !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initialize() {
        val btnStart = findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener {
            val iSecondActivity = Intent(this, SecondActivity::class.java)
            startActivity(iSecondActivity)
        }
    }
}