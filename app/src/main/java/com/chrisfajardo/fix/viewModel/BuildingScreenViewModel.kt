package com.chrisfajardo.fix.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chrisfajardo.fix.data.repo.BuildRepo
import com.chrisfajardo.fix.ui.users.UserBuildScreenActivity

class BuildingScreenViewModel : ViewModel() {


    private val repo = BuildRepo()
    fun fetchUserData(): LiveData<MutableList<UserBuildScreenActivity>> {
        val  mutableData = MutableLiveData<MutableList<UserBuildScreenActivity>>()
        repo.getUserData().observeForever {userList->
            mutableData.value= userList
        }
        return mutableData


    }
}