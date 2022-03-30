package com.chrisfajardo.fixjob.viewModel

import androidx.lifecycle.ViewModel
import com.chrisfajardo.fixjob.domain.FirestoreUserCase

class FirestoreViewModel:ViewModel() {

    val firestoreUserCase= FirestoreUserCase()

    fun createUser(name:String,phone:String,description:String,spinner:String,url:String){
        firestoreUserCase.setUserFirestore(name,phone,description,spinner,url)

    }

}