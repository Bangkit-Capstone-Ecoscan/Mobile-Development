package com.example.ecoscan.ui.screen.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.pref.DataResultScan
import com.example.ecoscan.data.pref.GetImageUrl
import com.example.ecoscan.data.pref.UserIdData
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.data.remote.response.AuthResponse
import com.example.ecoscan.data.remote.response.PredictResponse
import com.example.ecoscan.data.remote.response.ScanResultResponse
import com.example.ecoscan.data.remote.response.StoreImageResponse
import com.example.ecoscan.data.remote.response.StoreResultResponse
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File

class ScanViewModel (private val repository: EcoRepository):ViewModel() {

    private val _upload = MutableLiveData<UiState<ScanResultResponse>>()
    val upload: LiveData<UiState<ScanResultResponse>> = _upload
    fun scanImage(imageUrl: String) {
        viewModelScope.launch {
            repository.scanPredict(imageUrl).asFlow().collect {
                _upload.value = it
            }
        }
    }

    private val _storeImages = MutableLiveData<UiState<StoreImageResponse>>()
    val storeImages: LiveData<UiState<StoreImageResponse>> = _storeImages
    fun storeImage(file: File) {
        viewModelScope.launch {
            repository.storeImage(file).asFlow().collect {
                _storeImages.value = it
            }
        }
    }

    private val _storeResults = MutableLiveData<UiState<StoreResultResponse>>()
    val storeResults: LiveData<UiState<StoreResultResponse>> = _storeResults
    fun storeResult(calcium: String, carbohydrates: String, emissions: String, fat: String, food_name: String, protein: String, vitamins: String, image_url: String) {
        viewModelScope.launch {
            repository.storeResult(calcium, carbohydrates, emissions, fat, food_name, protein, vitamins, image_url).asFlow().collect {
                _storeResults.value = it
            }
        }
    }

   fun saveResult(resultScan: DataResultScan) {
        viewModelScope.launch {
            repository.saveResult(resultScan)
        }
    }

    fun getResult(): LiveData<DataResultScan> {
        return repository.getResult().asLiveData()
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun getQuota(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun saveImageUrl(imageUrl: GetImageUrl) {
        viewModelScope.launch {
            repository.saveImageUrl(imageUrl)
        }
    }

    fun getImageUrl(): LiveData<GetImageUrl> {
        return repository.getImageUrl().asLiveData()
    }

}