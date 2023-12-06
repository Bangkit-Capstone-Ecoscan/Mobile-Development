package com.example.ecoscan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.data.repository.EcoRepository

class EcoScanViewModel (private val repository: EcoRepository): ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }



}