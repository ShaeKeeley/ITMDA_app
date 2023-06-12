package com.example.insuranceapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.insuranceapp.database.DatabaseHelper

class SignupActivity : AppCompatActivity() {
    private lateinit var uname: EditText
    private lateinit var pword: EditText
    private lateinit var cpword: EditText
    private lateinit var Email: EditText
    private lateinit var PhoneNumber: EditText
    private lateinit var PolicyNumber: EditText
    private lateinit var signupbtn: Button
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerpage)

        val textView = findViewById<TextView>(R.id.txtLink)
        textView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        uname = findViewById(R.id.username)
        pword = findViewById(R.id.password)
        cpword = findViewById(R.id.password2)
        Email = findViewById(R.id.email)
        PhoneNumber = findViewById(R.id.phonenumber)
        PolicyNumber = findViewById(R.id.policynumber)
        signupbtn = findViewById(R.id.registerbutton)
        db = DatabaseHelper(this)

        signupbtn.setOnClickListener() {
            val unametext = uname.text.toString()
            val pwordtext = pword.text.toString()
            val cpwordtext = cpword.text.toString()
            val Emailtext = Email.text.toString()
            val PhoneNumbertext = PhoneNumber.text.toString()
            val PolicyNumbertext = PolicyNumber.text.toString()
            val savedata = db.insertdata(unametext, pwordtext, Emailtext, PhoneNumbertext, PolicyNumbertext)

            if (TextUtils.isEmpty(unametext) || TextUtils.isEmpty(pwordtext) || TextUtils.isEmpty(Emailtext) || TextUtils.isEmpty(PhoneNumbertext) || TextUtils.isEmpty(PolicyNumbertext) || TextUtils.isEmpty(
                    cpwordtext
                )
            ) {
                Toast.makeText(
                    this,
                    "Missing something",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (pwordtext.equals(cpwordtext)) {
                    if (savedata == true) {
                        Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "User Exists", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "User Exist", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}