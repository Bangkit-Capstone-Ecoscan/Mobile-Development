package com.example.ecoscan.data.repository

import com.example.ecoscan.data.pref.UserPreference
import com.example.ecoscan.data.remote.retrofit.ApiService

class EcoRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
){


    companion object {
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): EcoRepository = EcoRepository(apiService, userPreference)
    }
}