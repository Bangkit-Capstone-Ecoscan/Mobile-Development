package com.example.ecoscan.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.remote.response.ArticleResponse
import com.example.ecoscan.data.remote.response.ArticleResponseItem
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: EcoRepository): ViewModel(){

    private val _getArticle = MutableLiveData<UiState<List<ArticleResponseItem>>>()
    val getArticle: LiveData<UiState<List<ArticleResponseItem>>> get() = _getArticle
    fun getAllArticle(){
        viewModelScope.launch {
            try {
                repository.getAllArticle().asFlow().collect {
                    _getArticle.value = it
                }
            } finally {

            }

        }
    }
}