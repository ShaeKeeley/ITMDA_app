// claims submission page
package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity()

{
    var pickedPhoto : Uri? = null
    var pickedBitMap : Bitmap? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        descriptionFocusListener()

        binding.buttonSubmit.setOnClickListener { submitForm() }
    }


    //////////////// Image input /////////////////////////////////////
    fun pickPhoto(view: View){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1)
        } else {
            val galeriInText = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeriInText,2)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                val galeriIntext = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntext,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            pickedPhoto = data.data
            if (pickedPhoto != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(this.contentResolver,pickedPhoto!!)
                    pickedBitMap = ImageDecoder.decodeBitmap(source)
                    var imageView = findViewById<ImageView>(R.id.imageViewAdd)
                    imageView.setImageBitmap(pickedBitMap)
                }
                else {
                    pickedBitMap = MediaStore.Images.Media.getBitmap(this.contentResolver,pickedPhoto)
                    imageView.setImageBitmap(pickedBitMap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    /////////////////// Image input //////////////////////////////////

    private fun submitForm()
    {
        binding.descriptionContainer.helperText = validDescription()

        val validDescription = binding.descriptionContainer.helperText == null

        if (validDescription)
            resetForm()
        else
            invalidForm()
    }

    private fun invalidForm()
    {
        var message = ""
        if(binding.descriptionContainer.helperText != null)
            message += "\n\nDescription of Incident: " + binding.descriptionContainer.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                //do nothing
            }
            .show()
    }

    private fun resetForm()
    {
        var message = "Description: " + binding.descriptionEditText.text

        AlertDialog.Builder(this)
            .setTitle("Form Submitted")
            .setMessage(message)
            .setPositiveButton("Okay") { _, _ ->
                binding.descriptionEditText.text = null

                binding.descriptionContainer.helperText = getString(R.string.required)
            }
            .show()
    }

    private fun descriptionFocusListener(){
        binding.descriptionEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.descriptionContainer.helperText = validDescription()
            }
        }
    }

    private fun validDescription(): String? {
        val descriptionText = binding.descriptionEditText.text.toString()
        if (descriptionText.length < 0) {
            return "Please fill in the description of the incident"
        }
        return null
    }

        class MainActivity : AppCompatActivity() {
            private lateinit var databaseHelper: DatabaseHelper
            private lateinit var nameEditText: EditText
            private lateinit var saveButton: Button

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                databaseHelper = DatabaseHelper(this)
                nameEditText = findViewById<EditText>(R.id.descriptionEditText)
                saveButton = findViewById<Button>(R.id.buttonSubmit)

                saveButton.setOnClickListener {
                    val name = nameEditText.text.toString()
                    // Empty name
                    if (name.isNotEmpty()) {
                        val rowId = databaseHelper.insertUser(name)
                        if (rowId != -1L) {
                            // Saved successfully
                            nameEditText.text.clear()
                            showToast("Data saved successfully.")
                        } else {
                            // Failed to save
                            showToast("Failed to save data.")
                        }
                    } else showToast("Please enter a Description")
                }
            }

            override fun onDestroy() {
                super.onDestroy()
                databaseHelper.close()
            }

            private fun showToast(message: String) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

}