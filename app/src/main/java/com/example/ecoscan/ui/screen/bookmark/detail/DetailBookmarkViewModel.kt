package com.example.ecoscan.ui.screen.bookmark.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.ecoscan.data.pref.DataResultScan
import com.example.ecoscan.data.repository.EcoRepository
import kotlinx.coroutines.flow.Flow

class DetailBookmarkViewModel (private val repository: EcoRepository): ViewModel() {

    fun getResult():LiveData<DataResultScan> {
        return repository.getResult().asLiveData()
    }

}