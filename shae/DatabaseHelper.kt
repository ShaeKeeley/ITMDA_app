package com.example.insuranceapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "Userdata", null, 1) {
    override fun onCreate(po: SQLiteDatabase?) {
        po?.execSQL("CREATE TABLE Userdata (username TEXT PRIMARY KEY, password TEXT," +
                " Email TEXT, PhoneNumber TEXT, date TEXT, time TEXT, PostcodeSuburb TEXT, Address TEXT, description TEXT, image BLOB, claimImg BLOB)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS Userdata")
    }
    //insert
    fun insertdata(
        username: String,
        password: String,
        Email: String,
        PhoneNumber: String
    ): Boolean {
        val p0 = this.writableDatabase
        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)
        values.put("Email", Email)
        values.put("PhoneNumber", PhoneNumber)
        val result = p0.insert("Userdata", null, values)
        if (result == 1.toLong()) {
            return false
        }
        return true
    }

    fun insertdata2(
        CarModel : String,
        Year: String,
        LicencePlate: String,
        DateInsured: String,
    ): Boolean {
        val p0 = this.writableDatabase
        val values = ContentValues()
        values.put("username", CarModel)
        values.put("Year", Year)
        values.put("LicencePlate", LicencePlate)
        values.put("DateInsured", DateInsured)
        val result = p0.insert("Userdata", null, values)
        if (result == 1.toLong()) {
            return false
        }
        return true
    }

    fun insertData3(username: String, desc: String, imageBytes: ByteArray?) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("description", desc)
        values.put("claimsImg", imageBytes)
        db.update("Userdata", values, "username = ?", arrayOf(username))
    }
    fun insertData4(db: SQLiteDatabase?, username: String, date1: String, time1: String) {
        db?.execSQL("UPDATE Userdata SET date = '$date1', time = '$time1' WHERE username = '$username'")
    }
    fun insertData5(db: SQLiteDatabase?, username: String, PostcodeSuburb: String, Address1: String) {
        db?.execSQL("UPDATE Userdata SET PostcodeSuburb = '$PostcodeSuburb', Address = '$Address1' WHERE username = '$username'")
    }
    fun insertData6(username: String, imageBytes: ByteArray?) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("image", imageBytes)
        db.update("Userdata", values, "username = ?", arrayOf(username))
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

    fun getText(): Cursor? {
        val db = this.readableDatabase
        val query = "SELECT username, Email, PhoneNumber FROM Userdata"
        return db.rawQuery(query, null)
    }
}
