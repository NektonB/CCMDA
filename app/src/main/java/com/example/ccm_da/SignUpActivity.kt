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
import com.example.ccm_da.pojos.CUList
import com.example.ccm_da.pojos.Center
import com.example.ccm_da.pojos.User
import com.google.firebase.firestore.ktx.toObject

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
            checkCenterAvailability()
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

    private fun loadEditProfile() {
        val iEditProfile = Intent(this, EditProfileActivity::class.java)
        startActivity(iEditProfile)
    }

    private fun checkUserAvailability() {
        try {
            val centerNumber = findViewById<EditText>(R.id.etCenterNumber).text.toString()
            val userName = findViewById<EditText>(R.id.etUserName).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()

            var user: User = User()
            user.user_name = userName
            user.password = password
            user.ads_id = "1"

            val qUserCheck =
                DatabaseConn.getCenterUserListRef().whereEqualTo("center_number", centerNumber)

            qUserCheck.get().addOnSuccessListener { documents ->
                var cuList = CUList()
                if (documents.size() != 0) {

                    for (document in documents) {

                        cuList = document.toObject<CUList>()

                        if (centerNumber == cuList.center_number && userName == cuList.user_name) {
                            Toast.makeText(
                                this,
                                "You are already sing up.Please login.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            user.ut_id = "3"
                        }
                    }
                } else {
                    user.ut_id = "1"
                    cuList.user_name = userName
                    cuList.center_number = centerNumber
                }

                saveUser(user, cuList)
            }.addOnFailureListener { it ->
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(this.toString(), e.toString())
        }
    }

    private fun checkCenterAvailability() {
        try {
            val regNumber = findViewById<EditText>(R.id.etCenterNumber).text.toString()

            val qCenterCheck =
                DatabaseConn.getCenterRef().whereEqualTo("reg_number", regNumber)

            qCenterCheck.get().addOnSuccessListener { documents ->
                var center = Center()
                if (documents.size() != 0) {

                    for (document in documents) {

                        center = document.toObject<Center>()

                        if (regNumber == center.reg_number) {
                            checkUserAvailability()
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Center Not Found.! Please add center first....",
                        Toast.LENGTH_SHORT
                    ).show()
                }

//                saveUser(user, cuList)
            }.addOnFailureListener { it ->
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(this.toString(), e.toString())
        }
    }

    private fun saveUser(user: User, cuList: CUList) {
        try {
            if (user.ut_id != "" && user.ut_id != null) {
                DatabaseConn.getUserRef().document(user.user_name).set(user).addOnSuccessListener {
                    Toast.makeText(this, "User saved !", Toast.LENGTH_SHORT).show()
                    saveCUList(cuList)
                    //loadEditProfile()
                }.addOnFailureListener { it: Exception ->
                    Toast.makeText(
                        this,
                        "User not saved. \nSomething went wrong",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(this.toString(), e.toString())
        }
    }

    private fun saveCUList(cuList: CUList) {
        try {
            if ((cuList.user_name != "" && cuList.user_name != null) && (cuList.center_number != "" && cuList.center_number != null)) {
                DatabaseConn.getCenterUserListRef()
                    .document(cuList.center_number + "_" + cuList.user_name).set(cuList)
                    .addOnSuccessListener {
                        Toast.makeText(this, "User saved !", Toast.LENGTH_SHORT).show()
                        //loadEditProfile()
                    }.addOnFailureListener { it: Exception ->
                        Toast.makeText(
                            this,
                            "User not saved. \nSomething went wrong",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(this.toString(), e.toString())
        }
    }
}