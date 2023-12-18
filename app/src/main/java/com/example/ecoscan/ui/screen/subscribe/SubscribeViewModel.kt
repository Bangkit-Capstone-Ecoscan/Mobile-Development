package com.example.ecoscan.ui.screen.subscribe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.models.Paket
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SubscribeViewModel(private val repository: EcoRepository): ViewModel(){

    private val _uiState: MutableStateFlow<UiState<List<Paket>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Paket>>>
        get() = _uiState

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
}