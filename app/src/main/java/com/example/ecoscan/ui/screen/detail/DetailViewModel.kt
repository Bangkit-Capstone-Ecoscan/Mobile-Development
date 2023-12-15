package com.example.ecoscan.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.remote.response.DetailResponse
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: EcoRepository) : ViewModel() {

    private val _getDetailArticle = MutableLiveData<UiState<DetailResponse>>()
    val getDetailArticle: LiveData<UiState<DetailResponse>> get() = _getDetailArticle
    fun getDetailArticle(id: String) {
        viewModelScope.launch {
            try {
                repository.getDetailArticle(id).asFlow().collect {
                    _getDetailArticle.value = it
                }
            } finally {

            }
        }
    }

}