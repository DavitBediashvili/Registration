package com.example.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var Mail: EditText
    private lateinit var Password: EditText
    private lateinit var RepeatPassword: EditText
    private lateinit var SubmitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Mail = findViewById(R.id.EmailAddress)
        Password = findViewById(R.id.Password)
        RepeatPassword = findViewById(R.id.Password2)
        SubmitButton = findViewById(R.id.SubmitButton)


        SubmitButton.setOnClickListener {

            var mailInput = Mail.text.toString()
            var passwordInput = Password.text.toString()
            var password2Input = RepeatPassword.text.toString()

            if (mailInput.isEmpty() or (mailInput.length < 8) or !(mailInput.contains("@"))) {
                Mail.error = "Please write correct mail"
                return@setOnClickListener
            }
            else if (passwordInput.isEmpty() or (passwordInput.length <= 9) or !(passwordInput.matches(".*[0-9].*".toRegex())) or !(passwordInput.matches(".*[a-z].*".toRegex()))){
                Password.error = "Please write correct password"
                return@setOnClickListener
            }
            else if (password2Input != passwordInput){
                RepeatPassword.error = "Please repeat password correctly"
                return@setOnClickListener
            }
            else
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(mailInput, passwordInput)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "წარმატებით დარეგისტრირდი,კაი ბიჭი ხარ შენ", Toast.LENGTH_SHORT).show()
                        }
                        else
                            Toast.makeText(this, "ვერ დარეგისტრირდი,ცუდი ბიჭი ხარ შენ", Toast.LENGTH_SHORT).show()
                    }
        }
    }
}