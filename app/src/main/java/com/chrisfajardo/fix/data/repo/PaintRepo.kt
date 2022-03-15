package com.chrisfajardo.fix.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chrisfajardo.fix.ui.users.UserPaintScreenActivity
import com.google.firebase.firestore.FirebaseFirestore

class PaintRepo {

    fun getUserData( name:String): LiveData<MutableList<UserPaintScreenActivity>> {
        val mutableData = MutableLiveData<MutableList<UserPaintScreenActivity>>()
        FirebaseFirestore.getInstance().collection(name.lowercase())
            .get().addOnSuccessListener { result ->
                val listData = mutableListOf<UserPaintScreenActivity>()
                result.forEach{
                    val data=it.toObject(UserPaintScreenActivity::class.java)
                    listData.add(data)
                }
                mutableData.value=listData


                print(result)

            }
        return mutableData
    }
}