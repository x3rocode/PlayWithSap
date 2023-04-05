package com.example.playwithsap.network.model

import com.google.gson.annotations.SerializedName

data class LoginDto (
    @SerializedName("login")
    val login: String
)