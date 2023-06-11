package com.example.insuranceapp

import LoginActivity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.insuranceapp.database.DatabaseHelper

class SignupActivity : AppCompatActivity() {
    private lateinit var uname: EditText
    private lateinit var pword: EditText
    private lateinit var cpword: EditText
    private lateinit var signupbtn: Button
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_SignupActivity)

        uname = findViewById(R.id.PutunameHere)
        pword = findViewById(R.id.PutpwordHere)
        cpword = findViewById(R.id.PutcpwordHere)
        signupbtn = findViewById(R.id.PutSignInButtonIdHeRE)
        db = DatabaseHelper(this)

        signupbtn.setOnClickListener() {
            val unametext = uname.text.toString()
            val pwordtext = pword.text.toString()
            val cpwordtext = cpword.text.toString()
            val savedata = db.insertdata(unametext, pwordtext)

            if (TextUtils.isEmpty(unametext) || TextUtils.isEmpty(pwordtext) || TextUtils.isEmpty(
                    cpwordtext
                )
            ) {
                Toast.makeText(
                    this,
                    "Add Username, Password & Confirm Password",
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