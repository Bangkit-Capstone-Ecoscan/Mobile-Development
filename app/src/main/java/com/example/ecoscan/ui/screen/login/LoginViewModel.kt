package com.example.ecoscan.ui.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.data.remote.response.AuthResponse
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: EcoRepository):ViewModel() {

    private val _loginAccount = MutableLiveData<UiState<AuthResponse>>()
    val loginAccount: LiveData<UiState<AuthResponse>> = _loginAccount

    fun login(username: String, password: String) {
        viewModelScope.launch {
            repository.login(username,password).asFlow().collect {
                _loginAccount.value = it
            }
        }
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

}