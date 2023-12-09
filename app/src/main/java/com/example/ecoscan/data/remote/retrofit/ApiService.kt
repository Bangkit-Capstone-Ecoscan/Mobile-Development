package com.example.ecoscan.data.remote.retrofit


import com.example.ecoscan.data.remote.response.ArticleResponse
import com.example.ecoscan.data.remote.response.ArticleResponseItem
import com.example.ecoscan.data.remote.response.AuthResponse
import com.example.ecoscan.data.remote.response.DetailResponseItem
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    @GET("/article")
    suspend fun getAllArticle(): List<ArticleResponseItem>

    @GET("/article/detail")
    suspend fun getDetailArticle(@Query("title") title: String): List<DetailResponseItem>

}