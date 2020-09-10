package com.chrisfajardo.fix.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chrisfajardo.fix.data.repo.PaintRepo
import com.chrisfajardo.fix.ui.UserPaintScreenActivity

class PaintScreenViewModel:ViewModel() {


    private val repo = PaintRepo()
    fun fetchUserData():LiveData<MutableList<UserPaintScreenActivity>>{
        val  mutableData = MutableLiveData<MutableList<UserPaintScreenActivity>>()
        repo.getUserData().observeForever {userList->
            mutableData.value= userList
        }
        return mutableData


    }
}