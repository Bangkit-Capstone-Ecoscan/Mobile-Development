package com.example.ecoscan.di

import android.content.Context
import com.example.ecoscan.data.pref.UserPreference
import com.example.ecoscan.data.pref.dataStore
import com.example.ecoscan.data.remote.retrofit.ApiConfig
import com.example.ecoscan.data.repository.EcoRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): EcoRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first().token }
        val apiService = ApiConfig.getApiService(user)
        return EcoRepository.getInstance(apiService, pref)
    }
}

