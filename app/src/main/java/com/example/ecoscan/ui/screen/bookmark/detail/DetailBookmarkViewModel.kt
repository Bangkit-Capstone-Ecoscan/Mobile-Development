package com.example.ecoscan.ui.screen.bookmark.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.pref.DataResultScan
import com.example.ecoscan.data.pref.UserIdData
import com.example.ecoscan.data.remote.response.DetailResultResponse
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailBookmarkViewModel (private val repository: EcoRepository): ViewModel() {

    private val _getDetailById = MutableLiveData<UiState<DetailResultResponse>>()
    val getDetailById:LiveData<UiState<DetailResultResponse>> = _getDetailById
    fun detailResultById(id: String) {
        viewModelScope.launch {
            try {
                repository.getDetailScanById(id).asFlow().collect {
                    _getDetailById.value = it
                }
            } finally {

            }
        }
    }

    fun saveResult(userIdData: UserIdData) {
        viewModelScope.launch {
            repository.saveUserId(userIdData)
        }
    }

}