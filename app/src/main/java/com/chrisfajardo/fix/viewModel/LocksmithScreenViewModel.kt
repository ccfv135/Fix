package com.chrisfajardo.fix.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chrisfajardo.fix.data.repo.LocksmithRepo
import com.chrisfajardo.fix.ui.users.UserLocksmithScreenActivity

class LocksmithScreenViewModel : ViewModel() {


    private val repo = LocksmithRepo()
    fun fetchUserData(): LiveData<MutableList<UserLocksmithScreenActivity>> {
        val  mutableData = MutableLiveData<MutableList<UserLocksmithScreenActivity>>()
        repo.getUserData().observeForever {userList->
            mutableData.value= userList
        }
        return mutableData


    }
}