package com.example.formulaonefantasy

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class mainFragment : Fragment() {
    private val db = Firebase.firestore
    private lateinit var driverRecyclerAdapter: DriverRecyclerAdapter
    private lateinit var raceRecyclerAdapter: RaceRecyclerAdapter
    private lateinit var playerRecyclerAdapter: PlayerRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.mainRecyclerView)
        //val scheduleButton = view.findViewById<ImageButton>(R.id.calendarButton)

        view?.findViewById<ImageButton>(R.id.calendarButton)?.setOnClickListener{
            db.collection("races")
                .get()
                .addOnSuccessListener{
                    val raceList: ArrayList<Race> = ArrayList()
                    for(data in it.documents){
                        val race = data.toObject(Race::class.java)
                        if(race != null){
                            race.id = data.id
                            raceList.add(race)
                        }
                    }
                    raceRecyclerAdapter = RaceRecyclerAdapter(raceList)
                    recyclerView?.apply {
                        layoutManager = LinearLayoutManager(this@mainFragment)
                        adapter = raceRecyclerAdapter
                    }
                }
                .addOnFailureListener{
                    Log.e("Error getting races.", it.message.toString() )
                }
        }

        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}