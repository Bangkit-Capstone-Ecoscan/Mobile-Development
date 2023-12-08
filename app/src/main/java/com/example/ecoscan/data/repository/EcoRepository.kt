package com.example.ecoscan.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.data.pref.UserPreference
import com.example.ecoscan.data.remote.response.ArticleResponseItem
import com.example.ecoscan.data.remote.retrofit.ApiService
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.flow.Flow

class EcoRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }



    // Function To Create New Account
    suspend fun register(userName: String, firstName: String, lastName: String, password: String) =
        liveData {
            emit(UiState.Loading)
            try {
                val successResponse = apiService.register(userName, firstName, lastName, password)
                emit(UiState.Success(successResponse))
            } catch (e: Exception) {
                emit(UiState.Error("Error: ${e.message.toString()}"))
            }
        }

    // Function To Login
    suspend fun login(userName: String, password: String) = liveData {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.login(userName,password)
            emit(UiState.Success(successResponse))
        } catch (e: Exception) {
            emit(UiState.Error("Error : ${e.message.toString()}"))
        }
    }

    // Function To List Article
    suspend fun getAllArticle(): LiveData<UiState<List<ArticleResponseItem>>> = liveData{
        try {
            emit(UiState.Loading)
            val successResponse = apiService.getAllArticle()
            emit(UiState.Success(successResponse))
        }
        catch (e: Exception) {
            emit(UiState.Error("Error : ${e.message.toString()}"))
        }
    }




    companion object {
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference,
        ): EcoRepository = EcoRepository(apiService, userPreference)
    }
}