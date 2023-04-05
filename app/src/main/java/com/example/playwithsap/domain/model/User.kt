package com.example.playwithsap.domain.model

import javax.inject.Singleton

@Singleton
class User(
    val user: String,
    val password: String
) {
    companion object{
        @Volatile
        @JvmStatic
        private var userInfo: User? = null

        @JvmStatic
        @JvmOverloads
        fun getUser(user: String, password: String): User  = userInfo?: synchronized(this){
            userInfo ?: User(user, password).also { userInfo = it }
        }
    }
}