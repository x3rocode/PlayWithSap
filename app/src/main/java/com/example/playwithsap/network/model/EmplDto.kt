package com.example.playwithsap.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Empl(
    @SerializedName("MANDT")
    val mandt: String,

    @SerializedName("ZSABUN")
    val sabun: Int,

    @SerializedName("ZUUID")
    val uuid: String,

    @SerializedName("REGDAT")
    val regdate: String,

    @SerializedName("ZNAME")
    val name : String,

    @SerializedName("ZAGE")
    val age : Int,

    @SerializedName("ZHEIGHT")
    val height : Int,

    @SerializedName("ZEMAIL")
    val email : String
)