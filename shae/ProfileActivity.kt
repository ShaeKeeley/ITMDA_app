package com.example.insuranceapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var dbh: DatabaseHelper
    private lateinit var selectedImageUri: Uri
    private val IMAGE_REQUEST_CODE = 1
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        dbh = DatabaseHelper(this)

        val username1 = intent.getStringExtra("username")

        val cursor = dbh.getText()
        if (cursor != null) {
            val usernameColumnIndex = cursor.getColumnIndex("username")
            val emailColumnIndex = cursor.getColumnIndex("Email")
            val phoneNumberColumnIndex = cursor.getColumnIndex("PhoneNumber")

            var foundUsername = false

            while (cursor.moveToNext()) {
                val username = cursor.getString(usernameColumnIndex)
                val email = cursor.getString(emailColumnIndex)
                val phoneNumber = cursor.getString(phoneNumberColumnIndex)
                do {
                    val username = cursor.getString(usernameColumnIndex)
                    val email = cursor.getString(emailColumnIndex)
                    val phoneNumber = cursor.getString(phoneNumberColumnIndex)

                    Log.d("ProfileActivity", "Username: $username")
                    Log.d("ProfileActivity", "Email: $email")
                    Log.d("ProfileActivity", "PhoneNumber: $phoneNumber")

                    // ...

                } while (cursor.moveToNext())

                if (username == username1) {
                    foundUsername = true
                    val textView1 = findViewById<TextView>(R.id.fullName_field)
                    textView1.text = "Username: $username"

                    val textView4 = findViewById<TextView>(R.id.phoneNo_field)
                    textView4.text = "Phone Number: $phoneNumber"

                    val textView2 = findViewById<TextView>(R.id.email_field)
                    textView2.text = "Email: $email"
                    break
                }
            }

            if (!foundUsername) {
                Toast.makeText(
                    this,
                    "No contact found for the provided username",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(this, "No contacts available", Toast.LENGTH_SHORT).show()
        }

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            // Call the pickImageGallery() function to open the phone gallery
            pickImageGallery()
        }

        button3 = findViewById(R.id.Update)
        button3.setOnClickListener {
            val username = LoginActivity.edituser.text.toString()
            val imageBytes = selectedImageUri?.let { getImageBytes(it) }

            dbh.insertData6(username, imageBytes)
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }
        val cancelButton = findViewById<Button>(R.id.cancel)
        cancelButton.setOnClickListener {
            cancelProfileUpdate()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data!!
        }


        }
    private fun getImageBytes(uri: Uri): ByteArray? {
        val inputStream = contentResolver.openInputStream(uri)
        return inputStream?.readBytes()
    }
    private fun cancelProfileUpdate() {
        // Clear the selected image URI
        selectedImageUri = Uri.EMPTY

        // Display a toast or perform any other desired action
        Toast.makeText(this, "Profile update canceled", Toast.LENGTH_SHORT).show()

        // Optionally, navigate back to the previous activity
        finish()
    }


    }

