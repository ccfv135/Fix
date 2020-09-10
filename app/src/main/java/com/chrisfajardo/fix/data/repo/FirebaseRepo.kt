package com.chrisfajardo.fix.data.repo

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepo {

    val db = FirebaseFirestore.getInstance()

    fun setUserData(name: String, phone: String, description: String, spinner: String) {

        val userHashMap = hashMapOf(
            "name" to name,
            "phone" to phone,
            "description" to description,
            "spinner" to spinner
        )


        db.collection(spinner)
            .add(userHashMap).addOnCompleteListener {
                if (it.isSuccessful) {
                    print("Successful")
                } else {
                    print(" Not Successful")
                }
            }
    }
}