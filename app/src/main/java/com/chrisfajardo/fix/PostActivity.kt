package com.chrisfajardo.fix

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_post.*


class PostActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {




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

        db = FirebaseFirestore.getInstance().document("Painting/Painters")



        publishButton.setOnClickListener {
            saveHero()
        }



        spineerServices = findViewById(R.id.spinnerServices)
        arrayAdapter =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, itemList)
        spineerServices?.adapter = arrayAdapter
        spineerServices?.onItemSelectedListener = this


    }

    private fun saveHero() {
        val name = nameRegistre.text.toString().trim()
        val phone = phonRegistre.text.toString().trim()
        val description = descRegistre.text.toString().trim()


        if (name.isEmpty()) {
            nameRegistre.error = "Please enter a name"
            return
        }


        val items = HashMap<String, Any>()
        items.put("name",name)
        items.put("phone",phone)
        items.put("description",description)
        items.put("service",spinner)
        try {
            db.collection("Painting").document("Painters").set(items)
                .addOnSuccessListener { void: Void? ->
                    Toast.makeText(
                        this,
                        "Successfully uploaded to the database :)",
                        Toast.LENGTH_LONG
                    ).show()
                }.addOnFailureListener { exception: java.lang.Exception ->
                Toast.makeText(
                    this,
                    exception.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }catch (e:Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }


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
