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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DriversFragment : Fragment(){
        private val db = Firebase.firestore
        private lateinit var driverRecyclerAdapter: DriverRecyclerAdapter
        private lateinit var auth: FirebaseAuth

        @SuppressLint("MissingInflatedId", "ResourceType")
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drivers, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.drivers_recycler_view)
        auth = FirebaseAuth.getInstance()


        db.collection("drivers").orderBy("team")
            .get()
            .addOnSuccessListener {
                val driverList: ArrayList<Drivers> = ArrayList()
                for(data in it.documents){
                    val driver = data.toObject(Drivers::class.java)
                    if(driver != null){
                        driver.id = data.id
                        driverList.add(driver)
                    }
                }
                driverRecyclerAdapter= DriverRecyclerAdapter(driverList)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = driverRecyclerAdapter
                }
            }
            .addOnFailureListener{
                Log.e("Error getting drivers", it.message.toString())
            }



        return view
    }

}