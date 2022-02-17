package com.chrisfajardo.fix.ui

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_post.*
import java.io.IOException
import java.util.*


class PostActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    private val CAMERA_REQUEST = 1000
    private val PERMISSION_PICK_IMAGE = 1001


    private val viewModel by lazy {
        ViewModelProviders.of(this).get(FirestoreViewModel::class.java)
    }

    internal var filePatch: Uri? = null
    lateinit var dialog: android.app.AlertDialog

    //Firebase images
    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference


    lateinit var spinner: String
    lateinit var db: DocumentReference


    private var spineerServices: Spinner? = null
    private var arrayAdapter: ArrayAdapter<String>? = null

    private var itemList = arrayOf(
        "Choose the Service", "painting",
        "building", "LockSmith", "Cleaning",
        "Care", "Carpentry", "Mechanic", "Plumbing"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        storage = FirebaseStorage.getInstance();
        storageReference = storage.reference

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()

        buttonOpenCamera.setOnClickListener({ v ->
            OpenCamera()
        })
        buttonUploadImage.setOnClickListener({ v ->
            uploadImage()
        })



        db = FirebaseFirestore.getInstance().document("Painting/Painters")



        publishButton.setOnClickListener {
            uploadImagefun()


        }



        spineerServices = findViewById(R.id.spinnerServices)
        arrayAdapter =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, itemList)
        spineerServices?.adapter = arrayAdapter
        spineerServices?.onItemSelectedListener = this


    }

    private fun uploadImagefun() {
        if (filePatch != null) {
            dialog.show()
            val reference = storageReference.child("images/" + UUID.randomUUID().toString())

            reference.putFile(filePatch!!).addOnSuccessListener { taskSnapshot ->
                reference.downloadUrl.addOnSuccessListener {
                    viewModel.createUser(
                        nameRegistre.text.toString(),
                        phonRegistre.text.toString(),
                        descRegistre.text.toString(),
                        spinner,
                        it.toString()
                    )

                }
                dialog.dismiss()
                Toast.makeText(this@PostActivity, "Uploaded", Toast.LENGTH_SHORT)
                    .show()

            }.addOnFailureListener { e ->
                dialog.dismiss()
                Toast.makeText(this@PostActivity, "Failed", Toast.LENGTH_SHORT)
                    .show()
            }.addOnProgressListener { taskSnapshot ->
                val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                dialog.setMessage("Uploaded $progress")
            }

        }

    }

    private fun uploadImage() {

        Dexter.withActivity(this).withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                if (report!!.areAllPermissionsGranted()) {


                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.type = "image/*"
                    startActivityForResult(
                        Intent.createChooser(intent, "Select Image"),
                        PERMISSION_PICK_IMAGE
                    )

                } else {
                    Toast.makeText(this@PostActivity, "Permission Denied!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token!!.continuePermissionRequest()
            }
        }).check()

    }

    private fun OpenCamera() {
        Dexter.withActivity(this).withPermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                if (report!!.areAllPermissionsGranted()) {
                    val values = ContentValues()
                    values.put(MediaStore.Images.Media.TITLE, "New Picture")
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera")
                    filePatch =
                        contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, filePatch)
                    startActivityForResult(cameraIntent, CAMERA_REQUEST)

                } else {
                    Toast.makeText(this@PostActivity, "Permission Denied!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token!!.continuePermissionRequest()
            }
        }).check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PERMISSION_PICK_IMAGE) {
                if (data != null) {
                    if (data.data != null) {
                        filePatch = data.data
                        try {
                            val bitmap =
                                MediaStore.Images.Media.getBitmap(contentResolver, filePatch)
                            profilePic.setImageBitmap(bitmap)

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    }
                }
            }
            if (requestCode == CAMERA_REQUEST) {

                try {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(contentResolver, filePatch)
                    profilePic.setImageBitmap(bitmap)

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
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

private fun View.setOnClickListener() {
    TODO("Not yet implemented")
}
