package com.example.formulaonefantasy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment(){
    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        auth = FirebaseAuth.getInstance()
        val loggedInEmail = auth.currentUser?.email
        val nickname = view.findViewById<TextView>(R.id.player_profile_nickname)
        val points = view.findViewById<TextView>(R.id.player_profile_points)
        val favorite = view.findViewById<TextView>(R.id.player_profile_favorite_driver)
        val selectBtn = view.findViewById<Button>(R.id.selectDriverButton)
        val favDriver = view.findViewById<EditText>(R.id.input_fav_driver)
        val logOutBtn = view.findViewById<Button>(R.id.logOutBtn)


        selectBtn.setOnClickListener {
            db.collection("players")
                .whereEqualTo("email", loggedInEmail)
                .get()
                .addOnSuccessListener { result->
                    for(data in result.documents){
                        val id = data.id
                        db.collection("players")
                            .document(id)
                            .update("favorite", favDriver.text.toString())
                            .addOnSuccessListener {
                                favorite.text = favDriver.text.toString()
                            }
                    }
                }
        }

        db.collection("players")
            .whereEqualTo("email", loggedInEmail)
            .get()
            .addOnSuccessListener { result ->
                for (data in result.documents) {
                    val player = data.toObject<Players>()
                    nickname.text = player?.nickname.toString()
                    points.text = player?.points.toString()
                    favorite.text = player?.favorite.toString()
                }
            }

        logOutBtn.setOnClickListener {
            requireActivity().run{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }


        return view
    }
}