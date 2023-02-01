package com.example.formulaonefantasy

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class StandingsFragment : Fragment() {
        private val db = Firebase.firestore
        private lateinit var playerRecyclerAdapter: PlayerRecyclerAdapter

        @SuppressLint("MissingInflatedId")
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_standings, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.standings_recycler_view)

        db.collection("players").orderBy("points")
            .get()
            .addOnSuccessListener {
                val playersList: ArrayList<Players> = ArrayList()
                for(data in it.documents){
                    val players = data.toObject(Players::class.java)
                    if(players!=null){
                        players.id = data.id
                        playersList.add(players)
                    }
                }

                playerRecyclerAdapter = PlayerRecyclerAdapter(playersList)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = playerRecyclerAdapter
                }
            }

            .addOnFailureListener{
                Log.e("Error getting players.", it.message.toString())
            }
    return view
    }
}