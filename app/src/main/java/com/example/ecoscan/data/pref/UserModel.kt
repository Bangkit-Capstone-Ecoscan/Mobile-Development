package com.example.ecoscan.data.pref

data class UserModel (
    val email: String,
    val token : String,
    val quota: Int,
    val isLogin: Boolean = false
)