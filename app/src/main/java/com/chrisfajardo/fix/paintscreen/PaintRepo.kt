package com.chrisfajardo.fix.paintscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class PaintRepo {
    fun getUserData(branch:String): LiveData<MutableList<UserPaintScreen>> {
        val  mutableData = MutableLiveData<MutableList<UserPaintScreen>>()
        FirebaseFirestore.getInstance().collection(branch).get(). addOnSuccessListener { result ->
            val listData = mutableListOf<UserPaintScreen>()
            for (document in result){
                val imageUrl = document.getString("imageUrl")
                val nombre = document.getString("nombre")
                val descripcion = document.getString("descripcion")
                val usuario = UserPaintScreen(
                    imageUrl!!,
                    nombre!!,
                    descripcion!!
                )
                listData.add(usuario)
            }
            mutableData.value= listData
        }
        return mutableData
    }
}