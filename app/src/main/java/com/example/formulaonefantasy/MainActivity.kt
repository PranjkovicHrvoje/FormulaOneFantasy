package com.example.formulaonefantasy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.driverList)
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DriverRecyclerAdapter(ArrayList())
        }

        val button = findViewById<Button>(R.id.selectDriverButton)
        val inputText = findViewById<EditText>(R.id.inputDriver)
        button.setOnClickListener{
            val text = inputText.text.toString()
            val request = ServiceBuilder.buildService(APIEndpoint::class.java)
            val call = request.getDrivers(text)
            call.enqueue(object : Callback<ArrayList<Drivers>>{
                override fun onResponse(
                    call: Call<ArrayList<Drivers>>,
                    response: Response<ArrayList<Drivers>>
                ){
                    if(response.isSuccessful){
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = DriverRecyclerAdapter(response.body()!!)
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<Drivers>>, t: Throwable) {
                    Log.e("Main Activity", t.message.toString())
                }
            }
            )
        }
    }
}