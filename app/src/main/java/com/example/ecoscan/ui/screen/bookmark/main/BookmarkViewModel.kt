package com.example.ecoscan.ui.screen.bookmark.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecoscan.data.pref.UserIdData
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.data.remote.response.GetResultResponseItem
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookmarkViewModel (private val repository: EcoRepository): ViewModel() {

    private val _ListResults = MutableLiveData<UiState<List<GetResultResponseItem>>>()
    val ListResults : LiveData<UiState<List<GetResultResponseItem>>> get()  = _ListResults
    fun getResult() {
        viewModelScope.launch {
            try {
                repository.getResultScan().asFlow().collect {
                    _ListResults.value = it
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