package com.example.ecoscan.ui.screen.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.pref.DataResultScan
import com.example.ecoscan.data.remote.response.AuthResponse
import com.example.ecoscan.data.remote.response.PredictResponse
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.launch
import java.io.File

class ScanViewModel (private val repository: EcoRepository):ViewModel() {

    private val _upload = MutableLiveData<UiState<PredictResponse>>()
    val upload: LiveData<UiState<PredictResponse>> = _upload
    fun uploadImage(file:File) {
        viewModelScope.launch {
            repository.scanPredict(file).asFlow().collect {
                _upload.value = it
            }
        }
    }

   fun saveResult(resultScan: DataResultScan) {
        viewModelScope.launch {
            repository.saveResult(resultScan)
        }
    }

//    fun uploadImage(file: File) = repository.scanPredict(file)

}