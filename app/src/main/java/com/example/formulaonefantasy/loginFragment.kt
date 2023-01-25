package com.example.formulaonefantasy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class loginFragment : Fragment() {
    private val db = Firebase.firestore
    private lateinit var playerRecyclerAdapter: PlayerRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val inputText = view.findViewById<EditText>(R.id.fragment_login_input_name)
        view.findViewById<Button>(R.id.fragment_login_input_button).setOnClickListener{
            val nick = inputText.text.toString()
            val favoriteDriver: String = ""
            val player = Player("", nick, favoriteDriver, 0)

            db.collection("players")
                .add(player)
                .addOnSuccessListener {
                    player.id = it.id
                    playerRecyclerAdapter.addItem(player)
                }
            }
        return view
    }
}