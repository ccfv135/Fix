package com.chrisfajardo.fix.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chrisfajardo.fix.ui.UserPaintScreenActivity
import com.google.firebase.firestore.FirebaseFirestore

class PaintRepo {

    fun getUserData(): LiveData<MutableList<UserPaintScreenActivity>> {
        val mutableData = MutableLiveData<MutableList<UserPaintScreenActivity>>()
        FirebaseFirestore.getInstance().collection("Painting")
            .document("Painters/painting/painting").get().addOnSuccessListener { result ->
                if (result.exists()) {
                    val listData = mutableListOf<UserPaintScreenActivity>()
                    val data = result.data
                    val name = data?.get("name").toString()
                    val phone = data?.get("phone").toString()
                    val description = data?.get("description").toString()
                    val user = UserPaintScreenActivity(
                        name,
                        phone,
                        description
                    )
                    listData.add(user)
                    mutableData.value = listData
                } else {
                    print("No Data")
                }
            }
        return mutableData
    }
}