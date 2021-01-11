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
import com.example.ccm_da.pojos.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.toObject

class SignUpActivity : AppCompatActivity() {
    var person: String = ""
    var fullName: String = ""
    var userType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        person = intent.extras?.get("PERSON").toString()
        initialize()
    }

    private fun initialize() {
        val btnSingUp: Button = findViewById(R.id.btnSingUp)
        val tvLogin: TextView = findViewById(R.id.tvLogin)
        val btnAddCenter: FloatingActionButton = findViewById(R.id.btnRegCenter)

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
        val iEmployeeProfile = Intent(this, EmployeeProfileActivity::class.java)

        iEmployeeProfile.putExtra("FN", fullName)
        iEmployeeProfile.putExtra("UT", userType)

        startActivity(iEmployeeProfile)
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

    private fun checkUserAvailability() {
        try {
            val centerNumber = findViewById<EditText>(R.id.etCenterNumber).text.toString()
            val userName = findViewById<EditText>(R.id.etRegNumber).text.toString()
            val password = findViewById<EditText>(R.id.etAddress).text.toString()
            fullName = findViewById<EditText>(R.id.etFullName).text.toString()


            var user: User = User()
            user.user_name = userName
            user.password = password
            user.ads_id = "1"

            val qUserCheck =
                DatabaseConn.getCenterUserRef().whereEqualTo("center_number", centerNumber)

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
                            if (person == "COORDINATOR") {
                                user.ut_id = "3"
                                userType = "Guest"
                            } else if (person == "DOCTOR") {
                                user.ut_id = "5"
                                userType = "Doctor"
                            }
                            cuList.user_name = userName
                        }
                    }
                } else {
                    user.ut_id = "1"
                    userType = "Admin"
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

    private fun saveUser(user: User, cuList: CUList) {
        try {
            if (user.ut_id != "" && user.ut_id != null) {
                DatabaseConn.getUserRef().document(user.user_name).set(user).addOnSuccessListener {

                    saveCUList(cuList)

                    /*Toast.makeText(this, "Enter User Save. ${user.ut_id} !", Toast.LENGTH_SHORT)
                        .show()*/

                    if (user.ut_id != "5" && user.ut_id != "4") {

                        var ceList = CEList()
                        ceList.center_number = cuList.center_number
                        ceList.user_name = cuList.user_name
                        saveCEList(ceList)

                    } else if (user.ut_id == "5") {

                        var cdList = CDList()
                        cdList.center_number = cuList.center_number
                        cdList.user_name = cuList.user_name
                        saveCDList(cdList)

                    } else if (user.ut_id == "4") {

                    }
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
                DatabaseConn.getCenterUserRef()
                    .document(cuList.center_number + "_" + cuList.user_name).set(cuList)
                    .addOnSuccessListener {
//                        Toast.makeText(this, "User saved !", Toast.LENGTH_SHORT).show()
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

    private fun saveCEList(ceList: CEList) {
        try {
            if ((ceList.user_name != "" && ceList.user_name != null) && (ceList.center_number != "" && ceList.center_number != null)) {
                DatabaseConn.getCenterEmployeeRef()
                    .document(ceList.center_number + "_" + ceList.user_name).set(ceList)
                    .addOnSuccessListener {
                        Toast.makeText(this, "User saved to CE List !", Toast.LENGTH_SHORT).show()
                        loadEditProfile()
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

    private fun saveCDList(cdList: CDList) {
        try {
            if ((cdList.user_name != "" && cdList.user_name != null) && (cdList.center_number != "" && cdList.center_number != null)) {
                DatabaseConn.getCenterDoctorRef()
                    .document(cdList.center_number + "_" + cdList.user_name).set(cdList)
                    .addOnSuccessListener {
                        Toast.makeText(this, "User saved to CD List !", Toast.LENGTH_SHORT).show()
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