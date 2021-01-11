package com.example.ccm_da.db_conn

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

object DatabaseConn {

    private val db = Firebase.firestore
    private val ad_statusRef: CollectionReference = db.collection("ad_status")
    private val appointmentRef: CollectionReference = db.collection("appointment")
    private val as_listRef: CollectionReference = db.collection("as_list")
    private val asl_statusRef: CollectionReference = db.collection("asl_status")
    private val available_slotRef: CollectionReference = db.collection("available_slot")
    private val doctorRef: CollectionReference = db.collection("doctor")
    private val employeeRef: CollectionReference = db.collection("employee")
    private val employee_postRef: CollectionReference = db.collection("employee_post")
    private val event_logRef: CollectionReference = db.collection("event_log")
    private val patientRef: CollectionReference = db.collection("patient")
    private val userRef: CollectionReference = db.collection("user")
    private val user_typeRef: CollectionReference = db.collection("user_type")
    private val centerRef: CollectionReference = db.collection("center")
    private val centerUserRef: CollectionReference = db.collection("center_users")
    private val centerEmployeeRef: CollectionReference = db.collection("center_employees")
    private val centerDoctorRef: CollectionReference = db.collection("center_doctors")
    private val centerPatientRef: CollectionReference = db.collection("center_patients")

    private val storage = Firebase.storage
    private val profilePictureRef: StorageReference = storage.reference.child("profile_image")

    fun getDatabase(): FirebaseFirestore {
        return db
    }

    fun getADStatusRef(): CollectionReference {
        return ad_statusRef
    }

    fun getUserRef(): CollectionReference {
        return userRef
    }

    fun getCenterRef(): CollectionReference {
        return centerRef
    }

    fun getCenterUserRef(): CollectionReference {
        return centerUserRef
    }

    fun getCenterEmployeeRef(): CollectionReference {
        return centerEmployeeRef
    }

    fun getCenterDoctorRef(): CollectionReference {
        return centerDoctorRef
    }

    fun getCenterPatientRef(): CollectionReference {
        return centerPatientRef
    }


    fun getProfilePictureRef(): StorageReference {
        return profilePictureRef
    }

}