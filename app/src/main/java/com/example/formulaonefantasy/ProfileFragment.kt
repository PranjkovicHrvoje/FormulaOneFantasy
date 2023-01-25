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
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private val db = Firebase.firestore
    private lateinit var profileRecyclerAdapter: PlayerRecyclerAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.fragment_profile_recyclerview)
        db.collection("players")
            .get()
            .addOnSuccessListener {
                val profileList: ArrayList<Player> = ArrayList()
                for(data in it.documents){
                    val profile = data.toObject(Player::class.java)
                    if(profile!=null){
                        profile.id = data.id
                        profileList.add(profile)
                    }
                }
                profileRecyclerAdapter = PlayerRecyclerAdapter(profileList)
                recyclerView.apply {
                    LayoutManager = LinearLayoutManager(activity)
                    adapter = profileRecyclerAdapter
                }
            }

            .addOnFailureListener{
                Log.e("Error getting profile.", it.message.toString() )
            }

        return view
    }
}