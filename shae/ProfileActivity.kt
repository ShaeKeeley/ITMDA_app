package com.example.insuranceapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        displayData()
    }

    private fun displayData() {
        val dbHelper = DatabaseHelper(this)
        val username = "your_username" // Replace "your_username" with the actual username
        val dataList: List<String> = dbHelper.retrieveData(username)

        val fullNameField = findViewById<TextView>(R.id.fullName_field)
        val emailField = findViewById<TextView>(R.id.email_field)
        val phoneNoField = findViewById<TextView>(R.id.phoneNo_field)
        val policyNoField = findViewById<TextView>(R.id.policyNo_field)

        if (dataList.isNotEmpty()) {
            fullNameField.text = dataList[0]
            emailField.text = dataList[1]
            phoneNoField.text = dataList[2]
            policyNoField.text = dataList[3]
        }
    }

    fun goToHomePage() {
        displayData()
        // Handle button click here
    }
}