package com.example.ccm_da

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ccm_da.db_conn.DatabaseConn
import java.io.File
import java.io.FileInputStream

class EmployeeProfileActivity : AppCompatActivity() {

    private lateinit var tvEmployee: TextView
    private lateinit var tvUserType: TextView
    private lateinit var ivUpload: ImageView
    private lateinit var ivProfile: ImageView
    private lateinit var etFullName: EditText
    private lateinit var etContactNumber: EditText
    private lateinit var etNIC: EditText
    private lateinit var etAddress: EditText
    private lateinit var btnFinish: Button

    private var fullName: String = ""
    private var userType: String = ""
    private val pickImage = 100
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_profile)
        fullName = intent.extras?.get("FN").toString()
        userType = intent.extras?.get("UT").toString()
        initialize()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            ivProfile.setImageURI(data?.data)
            uploadProfileImage(data?.data.toString())
            //Toast.makeText(this, data?.data?.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun initialize() {
        tvEmployee = findViewById(R.id.tvEmployee)
        tvUserType = findViewById(R.id.tvUserType)
        ivUpload = findViewById(R.id.ivUpload)
        ivProfile = findViewById(R.id.ivProfile)
        etFullName = findViewById(R.id.etFullName)
        etContactNumber = findViewById(R.id.etContactNumber)
        etNIC = findViewById(R.id.etNIC)
        etAddress = findViewById(R.id.etAddress)
        btnFinish = findViewById(R.id.brnFinish)

        tvEmployee.text = fullName
        tvUserType.text = userType
        etFullName.setText(fullName)

        selectUserImage()
    }

    private fun selectUserImage() {
        ivUpload.setOnClickListener {
            try {
                if (VERSION.SDK_INT >= VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED
                    ) {
                        //permission denied
                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                        //show popup to request runtime permission
                        requestPermissions(permissions, PERMISSION_CODE)
                    } else {
                        //permission already granted
                        pickImageFromGallery()
                    }
                } else {
                    //system OS is < Marshmallow
                    pickImageFromGallery()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(this.toString(), e.toString())
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private companion object {
        //image pick code
        private const val IMAGE_PICK_CODE = 1000

        //Permission code
        private const val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun uploadProfileImage(imagePath: String) {
        try {
            val stream = FileInputStream(File(imagePath))
            DatabaseConn.getProfilePictureRef().child("employee/").putStream(stream)
                .addOnFailureListener { it ->
                    Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
                }.addOnSuccessListener {
                    Toast.makeText(this, "Uploaded", Toast.LENGTH_LONG).show()
                }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(this.toString(), e.toString())
        }
    }
}