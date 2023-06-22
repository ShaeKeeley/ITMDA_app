package com.example.insuranceapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "Userdata", null, 1) {
    override fun onCreate(po: SQLiteDatabase?) {
        po?.execSQL("CREATE TABLE Userdata (username TEXT PRIMARY KEY, password TEXT, Email TEXT, PhoneNumber TEXT, PolicyNumber TEXT, CarModel TEXT, Year TEXT, LicencePlate TEXT, DateInsured TEXT, Description TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS Userdata")
    }

    fun insertdata(
        username: String,
        password: String,
        Email: String,
        PhoneNumber: String,
        PolicyNumber: String
    ): Boolean {
        val p0 = this.writableDatabase
        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)
        values.put("Email", Email)
        values.put("PhoneNumber", PhoneNumber)
        values.put("PolicyNumber", PolicyNumber)
        val result = p0.insert("Userdata", null, values)
        return result != -1L // Return true if the insertion was successful
    }
    fun insertdata2(
        CarModel : String,
        Year: String,
        LicencePlate: String,
        DateInsured: String,
        Description: String
    ): Boolean {
        val p0 = this.writableDatabase
        val values = ContentValues()
        values.put("username", CarModel)
        values.put("Year", Year)
        values.put("LicencePlate", LicencePlate)
        values.put("DateInsured", DateInsured)
        values.put("Description", Description)
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
    }

    @SuppressLint("Range")
    fun retrieveData(username2: String): List<String>  {
        val dataList = mutableListOf<String>()
        val p0 = this.readableDatabase
        val query = "SELECT username, Email, PhoneNumber, PolicyNumber FROM Userdata WHERE username = '$username2' "
        val cursor = p0.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val username = cursor.getString(cursor.getColumnIndex("username"))
            val email = cursor.getString(cursor.getColumnIndex("Email")) // Corrected column name
            val phoneNumber = cursor.getString(cursor.getColumnIndex("PhoneNumber")) // Corrected column name
            val policyNumber = cursor.getString(cursor.getColumnIndex("PolicyNumber"))

            dataList.add(username)
            dataList.add(email)
            dataList.add(phoneNumber)
            dataList.add(policyNumber)

            cursor.close()
            return dataList
        }
        return dataList // Return an empty list if no data is found
    }
    }