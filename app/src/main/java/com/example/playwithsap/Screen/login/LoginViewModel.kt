package com.example.playwithsap.Screen.login

import android.content.Context
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playwithsap.domain.model.Login
import com.example.playwithsap.domain.repository.LoginRepository
import com.example.playwithsap.domain.util.MyResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel constructor(
    private val loginRepository: LoginRepository
) : ViewModel(){

    private val _loginState = MutableStateFlow<MyResult<Login>>(MyResult.Loading())
    val loginState: StateFlow<MyResult<Login>> = _loginState

    var authToken = ""
    fun login(user: String, password: String, @ApplicationContext context: Context) {
        viewModelScope.launch {
            authToken = "Basic " + Base64.encodeToString("${user}:${password}".toByteArray(), Base64.NO_WRAP)

            _loginState.value = loginRepository.login(authToken)
        }
    }
}