package com.example.ecoscan.ui.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.data.repository.EcoRepository
import kotlinx.coroutines.launch

class ProfileViewModel (private val repository: EcoRepository): ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logoutSession(){
        viewModelScope.launch {
            repository.logout()
        }
    }

}