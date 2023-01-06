package com.example.formulaonefantasy

data class Race(
    val circuitId: String,
    val circuitName: String,
    val Location: List<LocationInfo>,
    val date: String
)

data class LocationInfo(
    val country: String
)
