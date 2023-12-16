package com.example.ecoscan.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.di.PredictInjection
import com.example.ecoscan.ui.screen.scan.ScanViewModel

class PredictModelFactory (private val repository: EcoRepository): ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ScanViewModel::class.java) -> {
                ScanViewModel(repository) as T
            }

            else -> {
                throw  IllegalArgumentException("Unknown ViewModel Class " + modelClass.name)
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PredictModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): PredictModelFactory {
            if (INSTANCE == null) {
                synchronized(PredictModelFactory::class.java) {
                    INSTANCE = PredictModelFactory(PredictInjection.provideRepository(context))
                }
            }
            return INSTANCE as PredictModelFactory
        }
    }
}