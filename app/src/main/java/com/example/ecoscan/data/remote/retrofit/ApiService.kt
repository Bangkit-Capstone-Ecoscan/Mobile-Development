package com.example.ecoscan.data.remote.retrofit


import com.example.ecoscan.data.remote.response.AuthResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("auth/signUp")
    suspend fun register(
        @Field("username") name: String,
        @Field("firstName") fistName: String,
        @Field("lastName") lastName: String,
        @Field("password") password: String
    ):AuthResponse


    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login (
        @Field("username") username: String,
        @Field("password") password: String,
    ): AuthResponse

}