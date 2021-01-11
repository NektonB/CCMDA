package com.example.ccm_da

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data!!.data!!
            ivProfile.setImageURI(imageUri)
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

        ivUpload.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }
    }
}