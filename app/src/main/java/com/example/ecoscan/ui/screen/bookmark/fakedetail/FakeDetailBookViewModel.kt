package com.example.ecoscan.ui.screen.bookmark.fakedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.ecoscan.data.pref.DataResultScan
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.ui.common.UiState

class FakeDetailBookViewModel (private val repository: EcoRepository): ViewModel() {

    fun getResult(): LiveData<DataResultScan> {
        return repository.getResult().asLiveData()
    }

}