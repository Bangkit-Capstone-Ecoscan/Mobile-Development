package com.example.ecoscan.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.repository.EcoRepository
import kotlinx.coroutines.launch

class ProfileViewModel (private val repository: EcoRepository): ViewModel() {

    fun logoutSession(){
        viewModelScope.launch {
            repository.logout()
        }
    }

}