package com.example.playwithsap.Screen.empl.info

import com.example.playwithsap.domain.model.User
import com.example.playwithsap.network.model.Empl

class EmplContract {
    data class State(
        val empls: List<Empl> = listOf(),
        val isLoding: Boolean = false,
        val userInfo: User
    )
}