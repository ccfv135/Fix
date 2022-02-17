package com.chrisfajardo.fix.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chrisfajardo.fix.data.repo.CleaningRepo
import com.chrisfajardo.fix.ui.users.UserCleaningScreenActivity

class CleaningScreenViewModel : ViewModel() {


    private val repo = CleaningRepo()
    fun fetchUserData(): LiveData<MutableList<UserCleaningScreenActivity>> {
        val  mutableData = MutableLiveData<MutableList<UserCleaningScreenActivity>>()
        repo.getUserData().observeForever {userList->
            mutableData.value= userList
        }
        return mutableData


    }
}