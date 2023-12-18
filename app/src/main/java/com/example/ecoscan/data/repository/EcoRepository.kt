package com.example.ecoscan.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ecoscan.data.models.Paket
import com.example.ecoscan.data.models.PaketDataSource.paket
import com.example.ecoscan.data.pref.DataResultScan
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.data.pref.UserPreference
import com.example.ecoscan.data.remote.response.ArticleResponseItem
import com.example.ecoscan.data.remote.response.DetailResponse
import com.example.ecoscan.data.remote.retrofit.ApiService
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

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

    suspend fun saveResult(resultScan: DataResultScan) {
        userPreference.saveResult(resultScan)
    }

    fun getResult():Flow<DataResultScan> {
        return userPreference.getResult()
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

    // Function To Detail Article
    suspend fun getDetailArticle(id: String): LiveData<UiState<DetailResponse>> = liveData{
        try {
            emit(UiState.Loading)
            val successResponse = apiService.getDetailArticle(id)
            emit(UiState.Success(successResponse))
        }catch (e: Exception) {
            emit(UiState.Error("Error : ${e.message.toString()}"))
        }
    }

    fun scanPredict(image: File) = liveData {
        emit(UiState.Loading)
        val requestFile = image.asRequestBody("image/jpg".toMediaType())
        val file = MultipartBody.Part.createFormData(
            "image",image.name,requestFile
        )
        try {
            val succesResponse = apiService.scanPredict(file)
            emit(UiState.Success(succesResponse))
        } catch (e: Exception) {
            emit(UiState.Error("Error: ${e.message.toString()}"))
        }
    }

    // Show List Package
    fun getAllPaket(): Flow<List<Paket>> {
        return flowOf(paket)
    }

    companion object {
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference,
        ): EcoRepository = EcoRepository(apiService, userPreference)
    }
}