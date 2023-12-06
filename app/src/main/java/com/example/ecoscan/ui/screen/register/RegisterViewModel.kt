package com.example.ecoscan.ui.screen.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.remote.response.AuthResponse
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.launch

class RegisterViewModel (private val repository: EcoRepository): ViewModel() {

    private val _createAccount = MutableLiveData<UiState<AuthResponse>>()
    val createAccount: LiveData<UiState<AuthResponse>> = _createAccount
    fun registerUser(userName: String, firstName: String, lastName: String, password: String) {
        viewModelScope.launch {
            try {
                repository.register(userName,firstName, lastName, password).asFlow().collect {
                    _createAccount.value = it
                }
            } finally {

            }
        }
    }

}