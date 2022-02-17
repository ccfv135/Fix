package com.chrisfajardo.fix.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chrisfajardo.fix.ui.users.UserCleaningScreenActivity
import com.google.firebase.firestore.FirebaseFirestore

class CleaningRepo {

    fun getUserData(): LiveData<MutableList<UserCleaningScreenActivity>> {
        val mutableData = MutableLiveData<MutableList<UserCleaningScreenActivity>>()
        FirebaseFirestore.getInstance().collection("Cleaning")
            .get().addOnSuccessListener { result ->
                val listData = mutableListOf<UserCleaningScreenActivity>()
                result.forEach{
                    val data=it.toObject(UserCleaningScreenActivity::class.java)
                    listData.add(data)
                }
                mutableData.value=listData


                print(result)

            }
        return mutableData
    }
}