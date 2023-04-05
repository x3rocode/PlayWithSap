package com.example.playwithsap.Screen.empl.info

import com.example.playwithsap.domain.model.Empl
import com.example.playwithsap.domain.model.User

class EmplContract {
    data class State(
        val empls: List<Empl> = listOf(),
        val isLoding: Boolean = false,
        val userInfo: User
    )
}