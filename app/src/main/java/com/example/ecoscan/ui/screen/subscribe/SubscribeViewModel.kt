package com.example.ecoscan.ui.screen.subscribe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.models.Paket
import com.example.ecoscan.data.remote.response.AuthResponse
import com.example.ecoscan.data.remote.response.PaketResponse
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SubscribeViewModel(private val repository: EcoRepository): ViewModel(){

    private val _uiState: MutableStateFlow<UiState<List<Paket>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Paket>>>
        get() = _uiState

    private val _paket = MutableLiveData<UiState<PaketResponse>>()
    val paket: LiveData<UiState<PaketResponse>> = _paket

    fun getAllPaket() {
        viewModelScope.launch {
            repository.getAllPaket()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderPaket ->
                    _uiState.value = UiState.Success(orderPaket)
                }
        }
    }

    fun paketQuota(paket: String){
        viewModelScope.launch {
            repository.paketQuota(paket).asFlow().collect{
                _paket.value = it
            }
        }
    }
}