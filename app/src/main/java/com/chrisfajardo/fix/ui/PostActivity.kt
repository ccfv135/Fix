package com.chrisfajardo.fix.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.chrisfajardo.fix.R
import com.chrisfajardo.fix.viewModel.FirestoreViewModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_post.*


class PostActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: FirestoreViewModel


    lateinit var spinner: String
    lateinit var db: DocumentReference


    private var spineerServices: Spinner? = null
    private var arrayAdapter: ArrayAdapter<String>? = null

    private var itemList = arrayOf(
        "Choose the Service", "painting",
        "Building", "LockSmith", "Cleaning",
        "Care", "Carpentry", "Mechanic", "Plumbing"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        viewModel = ViewModelProviders.of(this).get(FirestoreViewModel::class.java)



        db = FirebaseFirestore.getInstance().document("Painting/Painters")



        publishButton.setOnClickListener {
            viewModel.createUser(
                nameRegistre.text.toString(),
                phonRegistre.text.toString(),
                descRegistre.text.toString(),
                spinner
            )

        }



        spineerServices = findViewById(R.id.spinnerServices)
        arrayAdapter =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, itemList)
        spineerServices?.adapter = arrayAdapter
        spineerServices?.onItemSelectedListener = this


    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(applicationContext, "Nothing Select", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var items: String = parent?.getItemAtPosition(position) as String
        spinner = items
        Toast.makeText(applicationContext, "$items", Toast.LENGTH_LONG).show()
    }

}
