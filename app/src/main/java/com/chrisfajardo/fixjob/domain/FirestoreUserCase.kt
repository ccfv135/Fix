package com.chrisfajardo.fixjob.domain

import com.chrisfajardo.fixjob.data.repo.FirebaseRepo

class FirestoreUserCase {

    val repo = FirebaseRepo()

    fun setUserFirestore(name:String,phone:String,description:String,spinner:String,url:String){
        repo.setUserData(name,phone,description,spinner,url)


    }
}