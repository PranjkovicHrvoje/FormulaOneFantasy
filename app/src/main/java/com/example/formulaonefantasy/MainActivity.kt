package com.example.formulaonefantasy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    private lateinit var driverRecyclerAdapter: DriverRecyclerAdapter
    private lateinit var raceRecyclerAdapter: RaceRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView)

        val scheduleButton = findViewById<ImageButton>(R.id.calendarButton)
        scheduleButton.setOnClickListener{
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
                    recyclerView.apply {
                        layoutManager=LinearLayoutManager(this@MainActivity)
                        adapter = raceRecyclerAdapter
                    }
                }
                .addOnFailureListener{
                    Log.e("Error getting races.", it.message.toString() )
                }
        }

        val driverButton = findViewById<ImageButton>(R.id.inputDriverButton)
        driverButton.setOnClickListener{
            db.collection("drivers")
                .get()
                .addOnSuccessListener {
                    val list : ArrayList<Drivers> = ArrayList()
                    for (data in it.documents){
                        val driver = data.toObject(Drivers::class.java)
                        if(driver != null){
                            //driver.id = data.id
                            list.add(driver)
                        }
                    }

                    driverRecyclerAdapter = DriverRecyclerAdapter(list)
                    recyclerView.apply{
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = driverRecyclerAdapter
                    }
                }
                .addOnFailureListener{
                    Log.e("Error getting drivers.", it.message.toString())
                }
        }

    }
}