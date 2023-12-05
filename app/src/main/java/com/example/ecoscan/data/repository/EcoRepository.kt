package com.example.ecoscan.data.repository

import androidx.lifecycle.liveData
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.data.pref.UserPreference
import com.example.ecoscan.data.remote.response.AuthResponse
import com.example.ecoscan.data.remote.retrofit.ApiService
import com.example.ecoscan.ui.common.UiState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

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
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody().toString()
                val erroResponse = Gson()?.fromJson(errorBody, AuthResponse::class.java)
                emit(UiState.Error(erroResponse.toString()))
            }
            catch (e: Exception) {
                emit(UiState.Error("Error: ${e.message.toString()}"))
            }
        }

    // Function To Login
    suspend fun login(userName: String, password: String) = liveData {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.login(userName,password)
            emit(UiState.Success(successResponse))
        } catch (e:HttpException) {
            val errorBody  = e.response()?.errorBody().toString()
            val errorResponse = Gson()?.fromJson(errorBody, AuthResponse::class.java)
            emit(UiState.Error(errorResponse.toString()))
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