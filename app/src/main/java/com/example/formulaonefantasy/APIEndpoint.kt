package com.example.formulaonefantasy

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface APIEndpoint {
    @GET("/api/f1/drivers/{driver}.json")
    fun getDrivers(@Path("driver") driver: String): Call<ArrayList<Drivers>>

    @GET("api/f1/current.json")
    fun getRace(race: String): Call<ArrayList<Race>>
}

