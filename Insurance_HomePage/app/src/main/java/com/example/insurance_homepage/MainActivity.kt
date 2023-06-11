package com.example.insurance_homepage

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd_Asset: Button = findViewById<Button>(R.id.btnAdd_Asset)
        btnAdd_Asset.setOnClickListener{
            val intent = Intent(this, "Add assett page"::class.java)
            startActivity(intent)
        }

        val btnNew_Claim: Button = findViewById<Button>(R.id.btnNew_Claim)
        btnNew_Claim.setOnClickListener{
            val intent = Intent(this, "New claim page"::class.java)
            startActivity(intent)
        }

        val profile_img: ImageButton = findViewById(R.id.profile_img)
        profile_img.setOnClickListener{
            val intent = Intent(this, "profile page"::class.java)
            startActivity(intent)
        }

    }
}