package com.example.formulaonefantasy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val registerBtn: Button = findViewById(R.id.login_register_button)
        registerBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val loginBtn: Button = findViewById(R.id.login_login_button)
        loginBtn.setOnClickListener{
            performLogin()
        }
    }

    private fun performLogin(){
        val email = findViewById<EditText>(R.id.login_input_email)
        val password = findViewById<EditText>(R.id.login_input_password)

        if(email.text.isEmpty() && password.text.isEmpty()){
            Toast.makeText(this, "Email and password cannot be empty.", Toast.LENGTH_LONG).show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this){task->
            if(task.isSuccessful){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                Toast.makeText(
                    baseContext, "Success.",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                Toast.makeText(
                    baseContext, "Authentication failed.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Error occurred.", Toast.LENGTH_LONG).show()
            }
    }
}