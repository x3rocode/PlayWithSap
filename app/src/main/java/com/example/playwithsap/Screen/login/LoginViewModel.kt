package com.example.playwithsap.Screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playwithsap.domain.datastore.StoreSavedToken
import com.example.playwithsap.domain.model.Login
import com.example.playwithsap.domain.repository.LoginRepository
import com.example.playwithsap.domain.util.MyResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel(){

    private val _loginState = MutableStateFlow<MyResult<Login>>(MyResult.Idel())
    val loginState: StateFlow<MyResult<Login>> = _loginState
    val sharedPref = StoreSavedToken
    fun login() {
        viewModelScope.launch {
            _loginState.value = MyResult.Loading()
            _loginState.value = loginRepository.login()
        }
    }
}