package com.example.ecoscan.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ecoscan.data.models.Paket
import com.example.ecoscan.data.models.PaketDataSource.paket
import com.example.ecoscan.data.pref.DataResultScan
import com.example.ecoscan.data.pref.UserIdData
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.data.pref.UserPreference
import com.example.ecoscan.data.remote.response.ArticleResponseItem
import com.example.ecoscan.data.remote.response.DetailResponse
import com.example.ecoscan.data.remote.response.DetailResultResponse
import com.example.ecoscan.data.remote.response.GetResultResponseItem
import com.example.ecoscan.data.remote.retrofit.ApiService
import com.example.ecoscan.data.remote.retrofit.ApiService2
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class EcoRepository private constructor(
    private val apiService: ApiService,
    private val apiService2: ApiService2,
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

    suspend fun saveUserId(userIdData: UserIdData) {
        userPreference.saveUserId(userIdData)
    }

    fun getUserId(): Flow<UserIdData> {
        return userPreference.getUserId()
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
            val succesResponse = apiService2.scanPredict(file)
            emit(UiState.Success(succesResponse))
        } catch (e: Exception) {
            emit(UiState.Error("Error: ${e.message.toString()}"))
        }
    }

    fun storeImage(image: File) = liveData {
        emit(UiState.Loading)
        val requestFile = image.asRequestBody("file/jpg".toMediaType())
        val file = MultipartBody.Part.createFormData(
            "file",image.name,requestFile
        )
        try {
            val successResponse = apiService.storeImage(file)
            emit(UiState.Success(successResponse))
        } catch (e: Exception) {
            emit(UiState.Error("Error ${e.message.toString()}"))
        }
    }

    fun storeResult(calcium: String, carbohydrates: String, emissions: String, fat: String, food_name: String, protein: String, vitamins: String, image_url: String) = liveData {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.storeResult(calcium,carbohydrates,emissions,fat,food_name,protein,vitamins,image_url)
            emit(UiState.Success(successResponse))
        } catch (e: Exception) {
            emit(UiState.Error("Error ${e.message.toString()}"))
        }
    }

    suspend fun getResultScan(
//        token: String
    ): LiveData<UiState<List<GetResultResponseItem>>> = liveData {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.getResult(
//                token
            )
            emit(UiState.Success(successResponse))
        } catch (e: Exception) {
            emit(UiState.Error("Error ${e.message.toString()}"))
        }
    }

    suspend fun getDetailScanById(id: String): LiveData<UiState<DetailResultResponse>> = liveData {
        emit(UiState.Loading)
        try {
            val successResponse = apiService.getDetailResultById(id)
            emit(UiState.Success(successResponse))
        } catch (e: Exception) {
            emit(UiState.Error(" Error ${e.message.toString()}"))
        }
    }




    // Show List Package
    fun getAllPaket(): Flow<List<Paket>> {
        return flowOf(paket)
    }

    // Transaction Paket Quota
    fun paketQuota(paket: String) = liveData{
        emit(UiState.Loading)
        try {
            val successResponse = apiService.paketQuota(paket)
            emit(UiState.Success(successResponse))
        }catch (e: Exception){
            emit(UiState.Error("Error: ${e.message.toString()}"))
        }
    }

    companion object {
        fun getInstance(
            apiService: ApiService,
            apiService2: ApiService2,
            userPreference: UserPreference,
        ): EcoRepository = EcoRepository(apiService,apiService2, userPreference)
    }
}