package com.chrisfajardo.fix.viewModel

import androidx.lifecycle.ViewModel
import com.chrisfajardo.fix.domain.FirestoreUserCase

class FirestoreViewModel:ViewModel() {

    val firestoreUserCase= FirestoreUserCase()

    fun createUser(name:String,phone:String,description:String,spinner:String){
        firestoreUserCase.setUserFirestore(name,phone,description,spinner)

    }

}