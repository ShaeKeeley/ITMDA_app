import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.insuranceapp.R
import com.example.insuranceapp.database.DatabaseHelper

class LoginActivity : AppCompatActivity() {
    private lateinit var edituser: EditText
    private lateinit var editpword: EditText
    private lateinit var loginbtn: Button
    private lateinit var dbh: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginbtn = findViewById(R.id.loginButton)
        edituser = findViewById(R.id.username)
        editpword = findViewById(R.id.password)
        dbh = DatabaseHelper(this)

        loginbtn.setOnClickListener() {
            val useredtx = edituser.text.toString()
            val passedtx = editpword.text.toString()

            if (TextUtils.isEmpty(useredtx) || TextUtils.isEmpty(passedtx)) {
                Toast.makeText(this, "Add Username, Password", Toast.LENGTH_SHORT).show()
            } else {
                val checkuser = dbh.checkuserpass(useredtx, passedtx)
                if (checkuser) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, success::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Wrong Username and Password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val txtLink = findViewById<TextView>(R.id.txtLink)
        txtLink.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}
