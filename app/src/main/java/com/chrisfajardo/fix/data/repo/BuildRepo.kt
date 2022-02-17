package com.chrisfajardo.fix.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chrisfajardo.fix.ui.users.UserBuildScreenActivity
import com.google.firebase.firestore.FirebaseFirestore

class BuildRepo {

    fun getUserData(): LiveData<MutableList<UserBuildScreenActivity>> {
        val mutableData = MutableLiveData<MutableList<UserBuildScreenActivity>>()
        FirebaseFirestore.getInstance().collection("building")
            .get().addOnSuccessListener { result ->
                val listData = mutableListOf<UserBuildScreenActivity>()
                result.forEach{
                    val data=it.toObject(UserBuildScreenActivity::class.java)
                    listData.add(data)
                }
                mutableData.value=listData


                print(result)

            }
        return mutableData
    }
}