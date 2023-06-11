package com.example.insuranceapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "Userdata", null, 1) {
    override fun onCreate(po: SQLiteDatabase?) {
        po?.execSQL("CREATE TABLE Userdata (username TEXT PRIMARY KEY, password TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS Userdata")
    }

    //insert
    fun insertdata(username: String, password: String): Boolean {
        val p0 = this.writableDatabase
        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)
        val result = p0.insert("Userdata", null, values)
        if (result == 1.toLong()) {
            return false
        }
        return true
    }

    fun checkuserpass(username: String, password: String): Boolean {
        val p0 = this.writableDatabase
        val query = "select * from Userdata where username = '$username' and password='$password'"
        val cursor = p0.rawQuery(query, null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }}
    
    
    
//     or we can use
package com.example.insuranceapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "Userdata", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Userdata (username TEXT PRIMARY KEY, password TEXT, full_name TEXT, email TEXT, policy_number TEXT, phone_number TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Userdata")
        onCreate(db)
    }

    fun insertData(username: String, password: String, fullName: String, email: String, policyNumber: String, phoneNumber: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("username", username)
            put("password", password)
            put("full_name", fullName)
            put("email", email)
            put("policy_number", policyNumber)
            put("phone_number", phoneNumber)
        }
        val result = db.insert("Userdata", null, values)
        db.close()
        return result != -1L
    }

    fun checkUserPass(username: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM Userdata WHERE username = ? AND password = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.rawQuery(query, selectionArgs)
        val result = cursor.count > 0
        cursor.close()
        db.close()
        return result
    }

    fun getEmail(username: String): String {
        val db = readableDatabase
        val query = "SELECT email FROM Userdata WHERE username = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.rawQuery(query, selectionArgs)
        val email = if (cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndex("email"))
        } else {
            ""
        }
        cursor.close()
        db.close()
        return email
    }

    fun getFullName(username: String): String {
        val db = readableDatabase
        val query = "SELECT full_name FROM Userdata WHERE username = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.rawQuery(query, selectionArgs)
        val fullName = if (cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndex("full_name"))
        } else {
            ""
        }
        cursor.close()
        db.close()
        return fullName
    }

    fun getPolicyNumber(username: String): String {
        val db = readableDatabase
        val query = "SELECT policy_number FROM Userdata WHERE username = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.rawQuery(query, selectionArgs)
        val policyNumber = if (cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndex("policy_number"))
        } else {
            ""
        }
        cursor.close()
        db.close()
        return policyNumber
    }

    fun getPhoneNumber(username: String): String {
        val db = readableDatabase
        val query = "SELECT phone_number FROM Userdata WHERE username = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.rawQuery(query, selectionArgs)
        val phoneNumber = if (cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndex("phone_number"))
        } else {
            ""
        }
        cursor.close()
        db.close()
        return phoneNumber
    }
}
