package com.example.formulaonefantasy

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

class ScheduleFragment : Fragment() {
    private val db = Firebase.firestore
    private lateinit var raceRecyclerAdapter: RaceRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.schedule_recycler_view)
        db.collection("races").orderBy("date")
            .get()
            .addOnSuccessListener {
                val raceList: ArrayList<Race> = ArrayList()
                for(data in it.documents){
                    val race = data.toObject(Race::class.java)
                    if(race!=null){
                        race.id = data.id
                        raceList.add(race)
                    }
                }
                raceRecyclerAdapter = RaceRecyclerAdapter(raceList)
                recyclerView.apply {
                    LayoutManager = LinearLayoutManager(activity)
                    adapter = raceRecyclerAdapter
                }
            }

            .addOnFailureListener{
                Log.e("Error getting races.", it.message.toString() )
            }

        return view
    }
}