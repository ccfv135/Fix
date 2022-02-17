package com.chrisfajardo.fix.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chrisfajardo.fix.ui.users.UserLocksmithScreenActivity
import com.google.firebase.firestore.FirebaseFirestore

class LocksmithRepo {

    fun getUserData(): LiveData<MutableList<UserLocksmithScreenActivity>> {
        val mutableData = MutableLiveData<MutableList<UserLocksmithScreenActivity>>()
        FirebaseFirestore.getInstance().collection("LockSmith")
            .get().addOnSuccessListener { result ->
                val listData = mutableListOf<UserLocksmithScreenActivity>()
                result.forEach{
                    val data=it.toObject(UserLocksmithScreenActivity::class.java)
                    listData.add(data)
                }
                mutableData.value=listData


                print(result)

            }
        return mutableData
    }
}