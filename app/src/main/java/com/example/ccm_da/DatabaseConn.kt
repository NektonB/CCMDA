package com.example.ccm_da

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object DatabaseConn {
    private val db = Firebase.firestore
    private val dbRef: CollectionReference = db.collection("Notebook")

    fun getDatabase(): FirebaseFirestore {
        return db
    }

    fun getDatabaseRef(): CollectionReference {
        return dbRef
    }
}