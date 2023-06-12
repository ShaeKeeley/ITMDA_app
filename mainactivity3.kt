import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.insuranceapp.R
import com.example.insuranceapp.database.DatabaseHelper

class MainActivity : AppCompatActivity() {


    private lateinit var fullNameField: TextView
    private lateinit var policyNoField: TextView
    private lateinit var phoneNoField: TextView
    private lateinit var emailField: TextView

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize views
        fullNameField = findViewById(R.id.fullName_field)
        policyNoField = findViewById(R.id.policyNo_field)
        phoneNoField = findViewById(R.id.phoneNo_field)
        emailField = findViewById(R.id.email_field)

        // Initialize database helper
        databaseHelper = DatabaseHelper(this)

        // Fetch data from the database
        val userData = fetchDataFromDatabase()

        // Update views with fetched data
        userData?.let { data ->
            fullNameField.text = data.getString("username")
            policyNoField.text = data.getString("PolicyNumber")
            phoneNoField.text = data.getString("PhoneNumber")
            emailField.text = data.getString("Email")
        }
    }

    private fun fetchDataFromDatabase(): Cursor? {
        val database: SQLiteDatabase = databaseHelper.readableDatabase
        val query = "SELECT * FROM Userdata"
        return database.rawQuery(query, null)
    }

    val backButton = findViewById<Button>(R.id.back_button)
    backButton.setOnClickListener {
        // Handle button click event here
        goToHomePage()
    }

    fun goToHomePage() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}

//select * from userdata where email = var
