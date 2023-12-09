package com.example.ecoscan.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.data.remote.response.ArticleResponseItem
import com.example.ecoscan.data.remote.response.DetailResponseItem
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: EcoRepository) : ViewModel() {

    private val _getDetailArticle = MutableLiveData<UiState<List<DetailResponseItem>>>()
    val getDetailArticle: LiveData<UiState<List<DetailResponseItem>>> get() = _getDetailArticle
    fun getDetailArticle(title: String) {
        viewModelScope.launch {
            try {
                repository.getDetailArticle(title).asFlow().collect {
                    _getDetailArticle.value = it
                }
            } catch (e: Exception) {
                _getDetailArticle.value = UiState.Error("Error: ${e.message}")
            }
        }
    }

}