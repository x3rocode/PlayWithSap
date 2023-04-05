package com.example.playwithsap.domain.model

import java.util.Date

data class Empl(
    val mandt: String,
    val sabun: Int,
    val uuid: String,
    val regdate: Date,
    val name : String,
    val age : Int,
    val height : Int,
    val email : String
)