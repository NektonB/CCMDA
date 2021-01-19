package com.example.ccm_da

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ccm_da.db_conn.DatabaseConn
import com.example.ccm_da.pojos.Center
import com.google.firebase.firestore.ktx.toObject

class MedicalCenterReg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_center)

        initialize()
    }


    private fun initialize() {
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            checkCenterAvailability()
        }
    }

    private fun checkCenterAvailability() {
        try {
            var center = Center()

            val etRegNumber = findViewById<EditText>(R.id.etRegNumber)
            val etName = findViewById<EditText>(R.id.etName)
            val etAddress = findViewById<EditText>(R.id.etPassword)
            val etContactNumber = findViewById<EditText>(R.id.etPasswordConform)

            center.reg_number = etRegNumber.text.toString()
            center.name = etName.text.toString()
            center.contact_number = etContactNumber.text.toString()
            center.address = etAddress.text.toString()

            val qCenterCheck =
                DatabaseConn.getCenterRef().whereEqualTo("reg_number", etRegNumber.text.toString())

            qCenterCheck.get().addOnSuccessListener { documents ->
                if (documents.size() != 0) {
                    for (document in documents) {

                        center = document.toObject<Center>()

                        if (etRegNumber.text.toString() == center.reg_number) {
                            Toast.makeText(
                                this,
                                "The Center is already saved.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    saveCenter(center)
                }

            }.addOnFailureListener { it ->
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(this.toString(), e.toString())
        }
    }

    private fun saveCenter(center: Center) {
        try {
            DatabaseConn.getCenterRef()
                .document(center.reg_number).set(center)
                .addOnSuccessListener {

                    Toast.makeText(this, "Center Saved !", Toast.LENGTH_SHORT).show()

                    val iSignUp = Intent(this, SignUpActivity::class.java)
                    startActivity(iSignUp)
                }.addOnFailureListener { it: Exception ->
                    Toast.makeText(
                        this,
                        "User not saved. \nSomething went wrong",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(this.toString(), e.toString())
        }
    }
}