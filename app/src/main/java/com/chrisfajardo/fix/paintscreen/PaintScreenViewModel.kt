package com.chrisfajardo.fix.paintscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaintScreenViewModel:ViewModel() {


    private val repo = PaintRepo()
    fun fetchUserData():LiveData<MutableList<UserPaintScreen>>{
        val  mutableData = MutableLiveData<MutableList<UserPaintScreen>>()
        repo.getUserData("Painting").observeForever {
            mutableData.value= it
        }
        return mutableData


    }
}