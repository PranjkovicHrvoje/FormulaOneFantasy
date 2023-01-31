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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    private lateinit var playerRecyclerAdapter: PlayerRecyclerAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val backBtn: Button = findViewById(R.id.back_btn)
        backBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val registerBtn: Button = findViewById(R.id.register_btn)
        registerBtn.setOnClickListener{
            performSignUp()
        }

    }

        private fun performSignUp(){
            val email = findViewById<EditText>(R.id.register_input_email)
            val password = findViewById<EditText>(R.id.register_input_password)

            if(email.text.isEmpty() && password.text.isEmpty()){
                Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_LONG).show()
            }

            val inputEmail = email.text.toString()
            val inputPassword = password.text.toString()
            val nick: String = inputEmail.substringBefore("@")
            val newPlayer = Player("", nick, "", 0)
            auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    db.collection("players")
                        .add(newPlayer)
                        .addOnSuccessListener {
                            newPlayer.id = it.id
                            playerRecyclerAdapter.addItem(newPlayer)
                        }
                    db.collection("players")
                        .get()
                        .addOnSuccessListener {
                            val list: ArrayList<Player> = ArrayList()
                            for(data in it.documents){
                                val player = data.toObject(Player::class.java)
                                if(player!=null){
                                    player.id = data.id
                                    list.add(player)
                                }
                            }
                            playerRecyclerAdapter = PlayerRecyclerAdapter(list)
                        }
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
                    Toast.makeText(this, "Error occurred.", Toast.LENGTH_LONG).show()
                }
        }
}