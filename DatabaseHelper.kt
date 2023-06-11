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