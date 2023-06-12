@file:Suppress("DEPRECATION")

package com.example.insurance_homepage

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd_Asset: Button = findViewById<Button>(R.id.btnAdd_Asset)
        btnAdd_Asset.setOnClickListener {
            val intent = Intent(this, "Add assett page"::class.java)
            startActivity(intent)
        }

        val btnNew_Claim: Button = findViewById<Button>(R.id.btnNew_Claim)
        btnNew_Claim.setOnClickListener {
            val intent = Intent(this, "New claim page"::class.java)
            startActivity(intent)
        }

        val profile_img: ImageButton = findViewById(R.id.profile_img)
        profile_img.setOnClickListener {
            val intent = Intent(this, "profile page"::class.java)
            startActivity(intent)
        }
    }
}
abstract class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1
    }

    class MainActivity : AppCompatActivity() {
        private lateinit var databaseHelper: DatabaseHelper
        private lateinit var textView: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            databaseHelper = DatabaseHelper(this)
            textView = findViewById<TextView>(R.id.textView3)

            val userId = "from user page"// Replace with the ID you want to search

            val user = databaseHelper.getUserById(userId)

            if (user != null) {
                val userData = "User - ID: ${user.id}, Name: ${user.name}"
                textView.text = userData
            } else {
                textView.text = "User not found"
            }

            databaseHelper = DatabaseHelper(this)
            val database: SQLiteDatabase = databaseHelper.writableDatabase

            // Use the database object for database operations

            database.close()
        }

        override fun onDestroy() {
            super.onDestroy()
            databaseHelper.close()
        }
    }
}





