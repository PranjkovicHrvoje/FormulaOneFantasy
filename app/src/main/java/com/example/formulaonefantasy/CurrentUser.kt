package com.example.formulaonefantasy

data class CurrentUser(
    var id: String = "",
    val nickname: String? = null,
    var favorite: String? = null,
    val points: Int? = null
)
