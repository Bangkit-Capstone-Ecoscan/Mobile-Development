package com.example.ecoscan.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecoscan.EcoScanViewModel
import com.example.ecoscan.data.repository.EcoRepository
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.screen.bookmark.detail.DetailBookmarkViewModel
import com.example.ecoscan.ui.screen.detail.DetailViewModel
import com.example.ecoscan.ui.screen.home.HomeViewModel
import com.example.ecoscan.ui.screen.login.LoginViewModel
import com.example.ecoscan.ui.screen.profile.ProfileViewModel
import com.example.ecoscan.ui.screen.register.RegisterViewModel
import com.example.ecoscan.ui.screen.scan.ScanViewModel

class ViewModelFactory (private val repository: EcoRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(EcoScanViewModel::class.java) -> {
                EcoScanViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ScanViewModel::class.java) -> {
                ScanViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailBookmarkViewModel::class.java) -> {
                DetailBookmarkViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel Class " + modelClass.name)
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}