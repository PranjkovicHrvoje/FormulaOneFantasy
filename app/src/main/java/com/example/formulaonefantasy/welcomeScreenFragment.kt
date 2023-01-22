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

@Suppress("UNREACHABLE_CODE")
class welcomeScreenFragment : Fragment() {
    private val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome_screen, container, false)
        val inputText = view.findViewById<EditText>(R.id.inputNickname)
        val loginButton = view.findViewById<Button>(R.id.inputNicknameButton)
        return inflater.inflate(R.layout.fragment_welcome_screen, container, false)

        loginButton.setOnClickListener{
            val nick = inputText.text.toString()
            val player = Player("",nick, 0)
            db.collection("players")
                .add(player)
                .addOnSuccessListener {
                    player.id = it.id
                }
        }

        return view
    }
}